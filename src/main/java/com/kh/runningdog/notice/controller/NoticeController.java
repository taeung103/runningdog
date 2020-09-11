package com.kh.runningdog.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.runningdog.notice.model.service.NoticeService;
import com.kh.runningdog.notice.model.vo.Notice;
import com.kh.runningdog.notice.model.vo.NoticePage;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;

	//공지사항 리스트 페이지 이동, 출력
	@RequestMapping(value="nlist.do")
	public ModelAndView selectNoticeList(HttpServletRequest request, ModelAndView mv) {
		logger.info("nlist.do run...");
		
		//검색값 받기
		String search = request.getParameter("searchNotice");
		String keyword = request.getParameter("keyword");
		
		if(!(keyword == null || keyword == "")) {	//키워드가 있을 경우
			//keyword.replaceAll("\\p{Z}", ""); //사이 공백
			keyword = keyword.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");  //앞뒤 공백 제거
		}
		int currentPage = 1; //기본 현재 페이지
		
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//list 개수 받기 용 객체 생성
		NoticePage noticeSearch = new NoticePage();
		noticeSearch.setSearch(search);
		noticeSearch.setKeyword(keyword);
		
		int listCount = noticeService.selectNoticeListCount(noticeSearch);	//목록 갯수
		
		NoticePage noticePage = new NoticePage(currentPage, listCount); //현재 페이지와 총 갯수 보내서, startPage, endpage등.. 값 만들기
		
		//검색값 NoticePage 에 넣기 (mybatis mapper로 보낼때 파라메타값 하나만 보낼 수 있어서)
		noticePage.setSearch(search);
		noticePage.setKeyword(keyword);
		
		ArrayList<Notice> list = noticeService.selectNoticeList(noticePage);
		
		mv.addObject("list", list);
		mv.addObject("noticePage", noticePage);
		mv.setViewName("notice/noticeList");
		return mv;
	}
	
	//공지사항 상세 페이지 이동, 출력
	@RequestMapping(value="ndetail.do")
	public ModelAndView selectNoticeDetail(HttpServletRequest request, ModelAndView mv, NoticePage noticePage) {
		logger.info("ndetail.do run...");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		Notice notice = noticeService.selectNoticeOne(noticeNo);
		
		if(notice != null) {
			noticeService.updateNoticeReadCount(noticeNo);	//조회수 증가
			noticePage.setNoticeNo(noticeNo);	//이전, 다음글 번호 조회할 때 객체 하나로 보내야해서 추가
			
			Integer preNo = noticeService.selectNoticePre(noticePage); //이전글 번호 조회, int는 null값을 못 받아서 integer사용
			Integer nextNo = noticeService.selectNoticeNext(noticePage); //다음글 번호 조회
			if(preNo == null) preNo = 0;	//이전글이 없을 때 0으로 설정
			if(nextNo == null) nextNo = 0;	//다음글이 없울 때 0으로 설정 
			
			mv.addObject("preNo", preNo);
			mv.addObject("nextNo", nextNo);
			mv.addObject("notice", notice);
			mv.addObject("noticePage", noticePage);
			mv.setViewName("notice/noticeView");
		} else {
			mv.addObject("message", "공지사항 상세페이지 이동 실패");
			mv.setViewName("common/error");
		}
		return mv;
	}
	
	//공지사항 이전/다음 페이지 검색후 이동
	//@RequestMapping(value="nprenext.do")
	public ModelAndView selectNoticePreNext(HttpServletRequest request, ModelAndView mv) {
		return mv;
	}
	
	//공지사항 첨부파일 다운로드
	@RequestMapping(value="nfdown.do")
	public ModelAndView noticeFileDownload(HttpServletRequest request) {
		logger.info("nfdown.do run...");
		String renameFilename = request.getParameter("rfile");
		String originalFilename = request.getParameter("ofile");
		
		String savePath = request.getSession().getServletContext().getRealPath("resources/nupfiles");
		File downFile = new File(savePath + "\\" + renameFilename);
		
		ModelAndView mv = new ModelAndView("noticeFiledown", "downFile", downFile);
		mv.addObject("fileName", originalFilename);
		
		return mv;
	}
	
	//메인페이지에 필수, new 공지사항 출력하기
	//@RequestMapping(value="nstate.do")
	public ModelAndView selectNoticeState(ModelAndView mv) {
		return mv;
	}
	
	//공지사항 등록 페이지 이동
	@RequestMapping(value="ninview.do")
	public ModelAndView moveNoticeInsert(Notice notice, HttpServletRequest request, ModelAndView mv) {
		mv.setViewName("notice/noticeWrite");
		return mv;
	}
	
	
	//공지사항 등록
	@RequestMapping(value="ninsert.do", method=RequestMethod.POST)
	public String insertAdminNotice(Notice notice, HttpServletRequest request, @RequestParam Map<String, MultipartFile> fileMap) {
		logger.info("ninsert.do run...");
		String returnView = null;
		int i = 1;
		
		String savePath = request.getSession().getServletContext().getRealPath("resources/nupfiles"); //파일 저장할 폴더 위치
		
		for(String key : fileMap.keySet()) {
			if(fileMap.get(key).getOriginalFilename() != "") {
			String originalFilename = fileMap.get(key).getOriginalFilename();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String renamefilename = sdf.format(new java.sql.Date(System.currentTimeMillis())); //현재시간
			renamefilename += "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1); //확장자명
			
			try {
				fileMap.get(key).transferTo(new File(savePath + "\\" + renamefilename));
			} catch (Exception e) {
				e.printStackTrace();
			}
				switch (i) {
				case 1 : notice.setNoticeOriginalFilename1(originalFilename);
					     notice.setNoticeRenameFilename1(renamefilename); break;
				case 2 : notice.setNoticeOriginalFilename2(originalFilename);
						 notice.setNoticeRenameFilename2(renamefilename); break;
				case 3 : notice.setNoticeOriginalFilename3(originalFilename);
						 notice.setNoticeRenameFilename3(renamefilename); break;
			}
				i++;
			}
		}
		
		if(noticeService.insertNotice(notice) > 0) {
			returnView = "redirect:/nlist.do";
		} else {
			request.setAttribute("message", "새 공지사항 등록 처리 실패");
			returnView = "common/error";
		}
		
		return returnView;
	}
	
	
	//공지사항 수정 페이지 이동
	@RequestMapping(value="nupview.do")
	public ModelAndView moveNoticeUpdate(HttpServletRequest request, ModelAndView mv) {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice notice = noticeService.selectNoticeOne(noticeNo);
		if(notice != null) {
			mv.addObject("notice", notice);
			mv.setViewName("notice/noticeUpdate");
		} else {
			mv.addObject("message", "공지사항 수정페이지 이동 실패");
			mv.setViewName("common/error");
		}
		
		return mv;
	}
	
	//공지사항 수정
	@RequestMapping(value="nupdate.do")
	public String updateAdminNotice(Notice notice, HttpServletRequest request, @RequestParam Map<String, MultipartFile> fileMap) {
		logger.info("nupdate.do run...");
		String returnView = null;
		String savePath = request.getSession().getServletContext().getRealPath("resources/nupfiles"); //파일 저장할 폴더 위치
		int i = 1;
		
		String deleteFilename1 = request.getParameter("deleteFilename1");
		String deleteFilename2 = request.getParameter("deleteFilename2");
		String deleteFilename3 = request.getParameter("deleteFilename3");
		
		//삭제할 파일이 넘겨져왔을 때 삭제하기
		if(!(deleteFilename1 == null || deleteFilename1.length() == 0)) {
			System.out.println(notice.getNoticeRenameFilename1() + " 삭제");
			new File(savePath + "\\" + notice.getNoticeRenameFilename1()).delete();
			notice.setNoticeOriginalFilename1(null);
			notice.setNoticeRenameFilename1(null);
		}
		if(!(deleteFilename2 == null || deleteFilename2.length() == 0)) {
			System.out.println(notice.getNoticeRenameFilename2() + " 삭제");
			new File(savePath + "\\" + notice.getNoticeRenameFilename2()).delete();
			notice.setNoticeOriginalFilename2(null);
			notice.setNoticeRenameFilename2(null);
		}
		if(!(deleteFilename3 == null || deleteFilename3.length() == 0)) {
			new File(savePath + "\\" + notice.getNoticeRenameFilename3()).delete();
			notice.setNoticeOriginalFilename3(null);
			notice.setNoticeRenameFilename3(null);
		}
		
		//파일 저장순서 변경하기 (1이 삭제되고 2, 3이 남으면 1, 2 이렇게 변경)
		if(notice.getNoticeOriginalFilename1() == null) {	//1번이 없는 경우(삭제된 경우)
			if(notice.getNoticeOriginalFilename2() != null) {	//2번이 있는 경우
				notice.setNoticeOriginalFilename1(notice.getNoticeOriginalFilename2());
				notice.setNoticeRenameFilename1(notice.getNoticeRenameFilename2());
				if(notice.getNoticeOriginalFilename3() != null) {	//3번이 있는 경우
					notice.setNoticeOriginalFilename2(notice.getNoticeOriginalFilename3());
					notice.setNoticeRenameFilename2(notice.getNoticeRenameFilename3());
					notice.setNoticeOriginalFilename3(null);
					notice.setNoticeRenameFilename3(null);
				} else {	//3번이 없는 경우
					notice.setNoticeOriginalFilename2(null);
					notice.setNoticeRenameFilename2(null);
				}
			} else {	//2번도 없는 경우
				if(notice.getNoticeOriginalFilename3() != null) {	//3번만 있는 경우
					notice.setNoticeOriginalFilename1(notice.getNoticeOriginalFilename3());
					notice.setNoticeRenameFilename1(notice.getNoticeRenameFilename3());
					notice.setNoticeOriginalFilename3(null);
					notice.setNoticeRenameFilename3(null);
				}
			}
		} else {
			if(notice.getNoticeOriginalFilename2() == null) {	//2번이 없는 경우
				if(notice.getNoticeOriginalFilename3() != null) {	//3번이 있는 경우
					notice.setNoticeOriginalFilename2(notice.getNoticeOriginalFilename3());
					notice.setNoticeRenameFilename2(notice.getNoticeRenameFilename3());
					notice.setNoticeOriginalFilename3(null);
					notice.setNoticeRenameFilename3(null);
				}
			}
		}
		
		System.out.println("기존에 있던 공지사항 : " + notice);	
		//새로 첨부된 파일 있을 경우 파일 추가하기
		for(String key : fileMap.keySet()) {
			if(fileMap.get(key).getOriginalFilename() != "") {
			String originalFilename = fileMap.get(key).getOriginalFilename();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String renamefilename = sdf.format(new java.sql.Date(System.currentTimeMillis())); //현재시간
			renamefilename += "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1); //확장자명
			
			try {
				fileMap.get(key).transferTo(new File(savePath + "\\" + renamefilename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(i + ", "+ notice.getNoticeOriginalFilename1().length());
				switch (i) {
				case 1 : 
					if(notice.getNoticeOriginalFilename1().length() == 0) {	//1번 파일이 없을 때
						notice.setNoticeOriginalFilename1(originalFilename);
						notice.setNoticeRenameFilename1(renamefilename);
					} else { //1번파일이 있을 때
						if(notice.getNoticeOriginalFilename2().length() == 0) {	//2번파일이 없을 때
							notice.setNoticeOriginalFilename2(originalFilename);
							notice.setNoticeRenameFilename2(renamefilename);							
						} else {
							notice.setNoticeOriginalFilename3(originalFilename);
							notice.setNoticeRenameFilename3(renamefilename);							
						}
					}
					break;
				case 2 :
					if(notice.getNoticeOriginalFilename2().length() == 0) {	//2번파일이 없을 때
						notice.setNoticeOriginalFilename2(originalFilename);
						notice.setNoticeRenameFilename2(renamefilename);						
					} else {
						notice.setNoticeOriginalFilename3(originalFilename);
						notice.setNoticeRenameFilename3(renamefilename); 						
					}
					break;
				case 3 : 
						notice.setNoticeOriginalFilename3(originalFilename);
						notice.setNoticeRenameFilename3(renamefilename); 
					break;
			}
				System.out.println(i + " 새로 추가된 파일 : " + originalFilename );
				i++;
			}//if문
		} //for문
	
		System.out.println("변경된 공지사항 : " + notice);
		
		if(noticeService.updateNotice(notice) > 0) {
			returnView = "redirect:/nlist.do";
		} else {
			request.setAttribute("message", notice.getNoticeNo() + "번 공지사항 수정 실패");
			returnView = "common/error";
		}
		return returnView;
	}	
	
	//공지사항 삭제
	@RequestMapping(value="ndelete.do")
	public String deleteAdminNotice(HttpServletRequest request) {
		String returnView = null;
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		if(noticeService.deleteNotice(noticeNo) > 0) {
			String savePath = request.getSession().getServletContext().getRealPath("resources/nupfiles"); //저장된 파일 위치
			for(int i = 1; i < 4; i++ ) {
				String renameFilename = request.getParameter("rfile" + i);	//첨부파일 있나 확인하는 용도
				if(renameFilename != null) {
					new File(savePath + "\\" + renameFilename).delete();
				}
			}
			returnView = "redirect:/nlist.do";
		} else {
			request.setAttribute("message", "공지사항 삭제 처리 실패");
			returnView = "common/error";
		}

		return returnView;
	}	
	
	
}
