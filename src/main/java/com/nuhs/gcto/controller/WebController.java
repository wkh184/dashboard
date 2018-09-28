package com.nuhs.gcto.controller;

import java.lang.invoke.MethodHandles;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.unbescape.html.HtmlEscape;

import com.nuhs.gcto.dao.AuditUserDAO;
import com.nuhs.gcto.model.Dashboard;
import com.nuhs.gcto.model.Issue;
import com.nuhs.gcto.service.DashboardService;
import com.nuhs.gcto.service.IssueService;
import com.nuhs.gcto.service.PatientService;
import com.nuhs.gcto.service.UserService;

@Controller
public class WebController {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Autowired
	private IssueService issueService;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private UserService userService;

	@Autowired
	private PatientService patientService;

	@Autowired
	AuditUserDAO auditUserDAO;

	@RequestMapping("/")
	public String root(Locale locale) {
		logger.debug("root");
		//		return "redirect:/index.html";
		return "redirect:/index.html";
	}

	/** CIH Dashboard */
	@RequestMapping("corp/dashboard.html")
	public String dashboard(Model model) {
//		Dashboard dashboard = dashboardService.prepareDashBoard();
//		model.addAttribute("dashboard", dashboard);		
//		return "dashboard";
		return "construction";
	}

	/** Patient card */
	@RequestMapping("/patient/patient_card.html")
	public String patientCard(Model model) {
		model = patientService.simulateLoadPatient(model);
		return "patient/patient_card";
	}

	/** Home page. */
	@RequestMapping("/index.html")
	public String index() {
		return "index";
	}

	/** Guide page. */
	@RequestMapping("/guide.html")
	public String guide() {
		return "guide";
	}

	/** User zone index. */
	@RequestMapping("/user/index.html")
	public String userIndex() {
		return "user/index";
	}

	/** Administration zone index. */
	@RequestMapping("/admin/index.html")
	public String adminIndex() {
		return "admin/index";
	}

	/** Shared zone index. */
	@RequestMapping("/shared/index.html")
	public String sharedIndex() {
		return "shared/index";
	}

	/** Login form. */
	@RequestMapping("/login.html")
	public String login() {
		return "login";
	}

	/** Login form with error. */
	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
		logger.debug("logoutPage");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
		String adid = auth.getName();
		if (auth != null){      
			new SecurityContextLogoutHandler().logout(request, response, auth);  
		}  
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		logger.debug("logout {}", adid);
		auditUserDAO.createAuditUser(adid, "Logout", currentTimestamp);
		return "redirect:/index.html";  
	}  

	/** Simulation of an exception. */
	@RequestMapping("/simulateError.html")
	public void simulateError() {
		throw new RuntimeException("This is a simulated error message");
	}

	/** Error page. */
	@RequestMapping("/error.html")
	public String error(HttpServletRequest request, Model model) {
		logger.debug("error");
		model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append("<ul>");
		while (throwable != null) {
			errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
			throwable = throwable.getCause();
		}
		errorMessage.append("</ul>");
		model.addAttribute("errorMessage", errorMessage.toString());
		return "error";
	}

	/** Error page. */
	@RequestMapping("/403.html")
	public String forbidden() {
		logger.debug("forbidden");
		return "403";
	}

	@RequestMapping(value="/admin", method=RequestMethod.GET)    
	public String admin() {    

		return "admin";    
	}    

	// Dashboard
	@RequestMapping(value="/index.html",method = RequestMethod.GET)
	public String index(Model model){
		Dashboard dashboard = dashboardService.prepareDashBoard();
		model.addAttribute("dashboard", dashboard);		
		return "index";
	}

	@RequestMapping(value="/add_issue",method = RequestMethod.GET)
	public String addIssue(Model model){
		model.addAttribute("issue", new Issue());
		return "issue_new";
	}

	@PostMapping("/add_issue_post")
	public String issueCreate(Model model, @ModelAttribute Issue issue) {
		Issue issueAdded = issueService.addIssue(issue);
		logger.debug("issueID = {}", issueAdded.getId());
		List issues = issueService.getAllIssues();
		logger.debug("issues = {}", issues.size());
		model.addAttribute("issues", issues);
		return "issue_list";
	}

	@RequestMapping(value="/find_issue",method = RequestMethod.GET)
	public String findIssue(Model model){
		List issues = issueService.getAllIssues();
		model.addAllAttributes(issues);
		return "issue_new";
	}

	@RequestMapping(value="/corp/all_issues",method = RequestMethod.GET)
	public String allIssues(Model model){
		List issues = issueService.getAllIssues();
		logger.debug("issues = {}", issues.size());
		model.addAttribute("issues", issues);
		return "issue_list";
	}

	@RequestMapping(value="/construction",method = RequestMethod.GET)
	public String construction(Model model){
		return "construction";
	}

	/*
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String home(Model model){
		logger.debug("Home");
//		ResultResponse response = new ResultResponse();
//		response.setResult_id(1);
//		response.setAdid("test");
//		response.setPredictor("readm");
//		response.setResultResponse("yes");
//		Calendar calendar = Calendar.getInstance();
//		java.util.Date now = calendar.getTime();
//		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
//		response.setDtResponded(currentTimestamp);
//		ResultResponseDAO.addResultResponse(response);
		model = PatientService.simulateLoadPatient(model);
		return "patient_card";
//				User user = new User();
//				model.addAttribute("user", user);		
//				return "login";
	}

	@PostMapping("/login_post")
	public String loginSubmit(Model model, @ModelAttribute User user) {
		//TODO login logic, session management
		Dashboard dashboard = DashboardService.prepareDashBoard();
		model.addAttribute("dashboard", dashboard);		
		return "index";
	}

	@PostMapping("/signup")
	public String signup(Model model) {
		logger.debug("Signup");
		UserSignup userSignup = new UserSignup(); 
		model.addAttribute("userSignup", userSignup);	
		return "signup";
	}

	@PostMapping("/signup_token")
	public String signupSubmitToken(Model model) {
		logger.debug("New sign up token");
		UserSignup userSignupToken = new UserSignup();
		model.addAttribute("userSignupToken", userSignupToken);
		return "signup_token";
	}

	@PostMapping("/signup_post")
	public String signupSubmit(Model model, @ModelAttribute UserSignup userSignup) {
		logger.debug("New sign up post");
		UserSignup userSignupToken = UserService.handleNewSignUp(userSignup);
		UserSignup userSignupTokenADID = new UserSignup();
		userSignupTokenADID.setAdid(userSignupToken.getAdid());
		model.addAttribute("userSignupToken", userSignupTokenADID);
		return "signup_token";
	}

	@PostMapping("/invalidate")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}


	// Dashboard
	@RequestMapping(value="/index.html",method = RequestMethod.GET)
	public String index(Model model){
		Dashboard dashboard = DashboardService.prepareDashBoard();
		model.addAttribute("dashboard", dashboard);		
		return "index";
	}

	@RequestMapping(value="/add_issue",method = RequestMethod.GET)
	public String addIssue(Model model){
		model.addAttribute("issue", new Issue());
		return "issue_new";
	}

	@PostMapping("/add_issue_post")
	public String issueCreate(Model model, @ModelAttribute Issue issue) {
		Integer issueID = IssueDAO.addIssue(issue);
		logger.debug("issueID = {}", issueID.toString());
		List issues = IssueDAO.getAllIssues();
		logger.debug("issues = {}", issues.size());
		model.addAttribute("issues", issues);
		return "issue_list";
	}

	@RequestMapping(value="/find_issue",method = RequestMethod.GET)
	public String findIssue(Model model){
		List issues = IssueDAO.getAllIssues();
		model.addAllAttributes(issues);
		return "issue_new";
	}

	@RequestMapping(value="/all_issues",method = RequestMethod.GET)
	public String allIssues(Model model){
		List issues = IssueDAO.getAllIssues();
		logger.debug("issues = {}", issues.size());
		model.addAttribute("issues", issues);
		return "issue_list";
	}

	@RequestMapping(value="/construction",method = RequestMethod.GET)
	public String construction(Model model){
		return "construction";
	}
	 */
}