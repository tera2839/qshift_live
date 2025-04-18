package com.quickshift.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
import org.springframework.web.bind.annotation.RequestParam;

import com.quickshift.Session.MemberSession;
import com.quickshift.entity.AdminRequest;
import com.quickshift.entity.Calendar;
import com.quickshift.entity.Member;
import com.quickshift.entity.MemberRequest;
import com.quickshift.entity.Shift;
import com.quickshift.entity.Store;
import com.quickshift.entity.Timeplan;
import com.quickshift.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberSession mSession;
	private final AdminService aService;
	private final AuthenticationManager authenticationManager; 

	/*------------------------------------------------------------
	メンバー
	------------------------------------------------------------*/
	
	@GetMapping("/memberLogin")
	public String showMemberLogin(
			@RequestParam("storeId") Long storeId,
			Model model
			) {
		
		Store store = aService.findByStoreId(storeId);
		mSession.setStore(store);
<<<<<<< HEAD
		model.addAttribute("storeName",store.getName());
=======
		model.addAttribute("name",store.getName());
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
		
		return "memberLogin";
		
	}
	
	@PostMapping("/completeMemberLogin")
	public String completeMemberLogin(
			@RequestParam("pass") String pass,
			Model model
			) {
		
		Store store = mSession.getStore();
<<<<<<< HEAD
		if(!store.getPass().equals(aService.encoder(pass))) {
=======
		if(!aService.matches(pass, store.getPass())) {
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
			
			model.addAttribute("error", "パスワードが違います");
			return "memberLogin";
		}
		
		return "redirect:/memberSelectName";
	}
	
	@GetMapping("/memberSelectName")
	public String showMemberselectName(
			Model model
			) {
		
		List<Member> members = aService.findMemberByStore(mSession.getStore());
		model.addAttribute("members", members);
		
		return "memberSelectName";
	}
	
<<<<<<< HEAD
	@PostMapping("/memberHome")
	public String showMemberHome(
			@RequestParam("memberId") String strId
			) {
		
		Long id = Long.parseLong(strId);
		Member member = aService.findMemberById(id);
		mSession.setMember(member);
=======
	@RequestMapping("/memberHome")
	public String showMemberHome(
			@RequestParam(name = "memberId", required = false) String strId
			) {
		
		if(strId != null) {
			Long id = Long.parseLong(strId);
			Member member = aService.findMemberById(id);
			mSession.setMember(member);
		}
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
		
		return "memberHome";
	}
	
	/*------------------------------------------------------------
	シフト確認
	------------------------------------------------------------*/
	
	@GetMapping("/checkHome")
	public String showCheckHome(
			Model model
			) {
		
		List<Shift> shifts = aService.findShiftByStore(mSession.getStore());
		List<String> months = new ArrayList<String>();
		for(Shift shift : shifts) {
			String str = shift.getCalendar().getCyear() + ":" + shift.getCalendar().getCmonth();
			if(!months.contains(shift))
				months.add(str);
		}
		
		model.addAttribute("months", months);
		
		return "checkHome";
	}
	
	@PostMapping("/checkShift")
	public String showCheckShift(
			Model model,
			@RequestParam("year") String reqYear,
			@RequestParam("month") String reqMonth
			) {
		
		int year = Integer.parseInt(reqYear);
		int month = Integer.parseInt(reqMonth);
		
		
		List<Shift> validShifts = new ArrayList<Shift>();
		List<Shift> allShifts = aService.findShiftByStore(mSession.getStore());
		for(Shift shift : allShifts) {
			
			int sYear = shift.getCalendar().getCyear();
			int sMonth = shift.getCalendar().getCmonth();
			if(sYear == year && sMonth == month) {
				validShifts.add(shift);
			}
		}
		
		List<Member> members = aService.findMemberByStore(mSession.getStore());
		List<String> shifts = new ArrayList<String>();
		for(Shift shift : validShifts) {
			
			String fromTime = shift.getTimeplan().getFromTime();
			String toTime = shift.getTimeplan().getToTime();
			String date = shift.getCalendar().getCdate() + "";
			String name = shift.getMember().getName();
			
			String str = fromTime + ":" + toTime + ":" + date + ":" + name;
			shifts.add(str);
		}
		
		String yearMonth = reqYear + ":" + reqMonth;
		
		model.addAttribute("members", members);
		model.addAttribute("shifts",shifts);
		model.addAttribute("yearMonth", yearMonth);
		
		return "checkShift";
	}
	
	/*------------------------------------------------------------
	シフト提出、編集
	------------------------------------------------------------*/
	
	@GetMapping("/memberEditHome")
	public String showMemberEditHome(
			Model model
			) {
		
<<<<<<< HEAD
		List<Shift> shifts = aService.findShiftByStore(mSession.getStore());
		List<String> months = new ArrayList<String>();
		for(Shift shift : shifts) {
			String str = shift.getCalendar().getCyear() + ":" + shift.getCalendar().getCmonth();
			if(!months.contains(shift))
=======
		List<AdminRequest> requests = aService.findAdminRequestByStore(mSession.getStore());
		List<String> months = new ArrayList<String>();
		for(AdminRequest request : requests) {
			String str = request.getCalendar().getCyear() + ":" + request.getCalendar().getCmonth();
			if(!months.contains(str))
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
				months.add(str);
		}
		
		model.addAttribute("months",months);
		
		return "memberEditHome";
	}
	
<<<<<<< HEAD
	@GetMapping("/memberEdit")
=======
	@PostMapping("/memberEdit")
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
	public String showMemberEdit(
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			Model model
			) {
		
<<<<<<< HEAD
		String yearMonth = year + ":" + month;
		List<Timeplan> plans = aService.findTimeplanByStore(mSession.getStore());
		List<AdminRequest> requestList = aService.findAdminRequestByStore(mSession.getStore());
		List<String> requests = new ArrayList<String>();
		for(AdminRequest request : requestList) {
			
			String planId = request.getTimeplan().getId() + "";
			String date = request.getCalendar().getCdate() + "";
			String num = request.getNum() + "";
			
			String str = planId + ":" + date + ":" + num;
			requests.add(str);
		}
		
		model.addAttribute("requests", requests);
=======
		List<Timeplan> plans = aService.findTimeplanByStore(mSession.getStore());
		List<AdminRequest> adminReqList = aService.findAdminRequestByStore(mSession.getStore());
		List<String> adminReqs = new ArrayList<String>();
		for(AdminRequest request : adminReqList) {
			
			String reqYear = request.getCalendar().getCyear() + "";
			String reqMonth = request.getCalendar().getCmonth() + "";
			
			if(reqYear.equals(year) && reqMonth.equals(month)) {
				
				String planId = request.getTimeplan().getId() + "";
				String date = request.getCalendar().getCdate() + "";
				String num = request.getNum() + "";
				
				String str = planId + ":" + date + ":" + num;
				adminReqs.add(str);
			}
		}
		List<MemberRequest> memberReqList = aService.findMemberRequestByMember(mSession.getMember());
		List<String> memberReqs = new ArrayList<String>();
		for(MemberRequest request : memberReqList) {
			
			String reqYear = request.getCalendar().getCyear() + "";
			String reqMonth = request.getCalendar().getCmonth() + "";
			
			if(reqYear.equals(year) && reqMonth.equals(month)) {
				
				String planId = request.getTimeplan().getId() + "";
				String date = request.getCalendar().getCdate() + "";
				
				String str = date + ":" + planId;
				memberReqs.add(str);
			}
		}
		
		model.addAttribute("plans", plans);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("adminReqs", adminReqs);
		model.addAttribute("memberReqs", memberReqs);
		
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
		
		return "memberEdit";
	}
	
	@PostMapping("/completeMemberEdit")
	public String showCompleteMemberEdit(
<<<<<<< HEAD
			@RequestParam("request") String[] requests
			) {
		
		for(String req : requests) {
			
			String[] str = req.split(":");
			Calendar calendar = aService.findCalendarById(Long.parseLong(str[0]));
			Timeplan plan = aService.findByTimeplanId(Long.parseLong(str[1]));
			
			MemberRequest request = new MemberRequest();
			request.setMember(mSession.getMember());
			request.setStore(mSession.getStore());
			request.setCalendar(calendar);
			request.setTimeplan(plan);
			
			aService.saveMemberRequest(request);
=======
			@RequestParam(name = "request", required = false) String[] requests,
			@RequestParam("year") String year,
			@RequestParam("month") String month
			) {
		
		List<MemberRequest> memberReqs = aService.findMemberRequestByMember(mSession.getMember());
		if(memberReqs.size() > 0) {
			for(MemberRequest request : memberReqs) {
				
				String reqYear = request.getCalendar().getCyear() + "";
				String reqMonth = request.getCalendar().getCmonth() + "";
				
				if(reqYear.equals(year) && reqMonth.equals(month)) {
					
					aService.deleteMemberRequest(request);
				}
			}
		}
		
		if(requests.length > 0) {
			for(String req : requests) {
				
				String[] str = req.split(":");
				Calendar calendar = aService.findCalendarById(Long.parseLong(str[0]));
				Timeplan plan = aService.findByTimeplanId(Long.parseLong(str[1]));
				
				MemberRequest request = new MemberRequest();
				request.setMember(mSession.getMember());
				request.setStore(mSession.getStore());
				request.setCalendar(calendar);
				request.setTimeplan(plan);
				
				aService.saveMemberRequest(request);
			}
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
		}
		
		return "redirect:/resultMemberEdit";
	}
	
<<<<<<< HEAD
	@PostMapping("/resultMemberEdit")
=======
	@GetMapping("/resultMemberEdit")
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
	public String showResultMemberEdit() {
		
		return"/resultMemberEdit";
	}
}