//package com.poscodx.mysite.controller;
//
//import com.poscodx.mysite.service.BoardService;
//import com.poscodx.mysite.service.GuestbookService;
//import com.poscodx.mysite.vo.BoardVo;
//import com.poscodx.mysite.vo.GuestbookVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/board")
//public class BoardController {
//	@Autowired
//	private BoardService boardService;
//
//	@RequestMapping("")
//	public String main(Model model) {
//		List<BoardVo> list = BoardService.getContentsList();
//		model.addAttribute("list", list);
//		return "board/main";
//	}
//
//	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
//	public String delete(@PathVariable("no") Long no, Model model) {
//		model.addAttribute("no", no);
//		return "board/delete";
//	}
//
//	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
//	public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
//		guestbookService.deleteContents(no, password);
//		return "redirect:/board";
//	}
//
//	@RequestMapping("add")
//	public String add(GuestbookVo vo) {
//		guestbookService.addContents(vo);
//		return "redirect:/board";
//	}
//
//}
