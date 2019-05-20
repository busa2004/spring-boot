package com.douzone.df.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.df.model.Eval;
import com.douzone.df.model.EvalItem;
import com.douzone.df.model.EvalItemScore;
import com.douzone.df.payload.EvalScoreRequest;
import com.douzone.df.payload.EvalVersionRequest;
import com.douzone.df.service.EvalService;




@RestController
@RequestMapping("/api/eval")
public class EvalController {

	@Autowired
	private EvalService service;
	
	/**********************************************
	 * EvalItem CRD
	 */
	// CREATE
	// parameter -> ?content=등록할 항목의 내용
	
	@RequestMapping(value = "/createEvalItem", method=RequestMethod.POST)
	public boolean createEvalItem(EvalItem evalItem) {
		boolean result = service.createEvalItem(evalItem);

		return result; // 정상실행 : true / 실패 : false
	}

	// READ
	// parameter -> ?itemNo=찾고싶은 evalItem..no값
	@RequestMapping(value = "/searchEvalItem", method=RequestMethod.POST)
	public EvalItem searchEvalItem(EvalItem evalItem) {
		EvalItem findItem = service.findItem(evalItem);
		
		return findItem; // 만약 null이 넘어온다면 객체를 찾지 못한 경우
	}

	// DELETE
	// parameter -> ?itemNo=삭제하고싶은 evalItem..no값	
	@RequestMapping(value = "/deleteEvalItem", method=RequestMethod.POST)
	public boolean deleteEvalItem(EvalItem evalItem) {
		
		boolean result = service.deleteEvalItem(evalItem);
		
		return result;
	}

	/**********************************************
	 * EvalItemScore CRUD
	 */
	// CREATE
	// parameter -> ?score=점수&evalItem=해당 항목의 no값
	@RequestMapping(value = "/inputEvalScore", method=RequestMethod.POST)
	public boolean inputEvalItemScore(EvalItemScore evalItemScore) {
		boolean result = service.createEvalItemScore(evalItemScore);
		
		return result; // 정상실행 : true / 실패 : false
	}
	@PostMapping("/setScore")
	public void setScore(@RequestBody EvalScoreRequest request) {
		System.out.println(request);
	}

	// READ
	// parameter -> ?no=evalScoreItem의 no값
	@RequestMapping(value = "/searchEvalScore", method=RequestMethod.POST)
	@ResponseBody
	public EvalItemScore searchEvalScore(EvalItemScore evalItemScore) {
		// url로 넘어온 no값으로 객체 찾기		
		EvalItemScore findItemScore = service.findItemScore(evalItemScore);
		
		return findItemScore; // 만약 null이 넘어온다면 객체를 찾지 못한 경우
	}

	// UPDATE
	// parameter : ?no=수정할 evalItemScore..no값&score=수정할 score
	@RequestMapping(value = "/updateEvalScore", method=RequestMethod.POST)
	public EvalItemScore updateEvalScore(EvalItemScore evalItemScore, Long score) {
		// url로 넘어온 no값으로 객체 찾기		
		EvalItemScore findItemScore = service.findItemScore(evalItemScore);
		
		// score 수정
		findItemScore = service.updateEvalItemScore(findItemScore, score);
		
		return findItemScore;		
	}

	// DELETE
	// parameter -> ?no=삭제할 evalItemScore..no값
	@RequestMapping(value="/deleteEvalScore", method=RequestMethod.POST)
	public boolean deleteEvalScore(EvalItemScore evalItemScore) {
		boolean result = service.deleteEvalItemScore(evalItemScore);		
		
		return result;
	}
	

	/**********************************************
	 * EvalItemVersion CRD
	 */
	// CREATE
	// parameter -> ?version=버전명&evalItem=항목들
	@PostMapping("/createVersion")
	public boolean createVersion(@RequestBody EvalVersionRequest request) {
		boolean result = service.createEvalVersion(request);
		
		return result; // 정상실행 : true / 실패 : false
	}

	// READ
	// parameter -> ?version=1.0
	@GetMapping("/getVersion")
	public List<EvalItem> getVersion(@RequestParam("version")String version) {
		List<EvalItem> findEV = service.findEvalItemByVersion(version);
		
		return findEV;
	}	
	
	// READ All
	@GetMapping("/getAllVersion")
	public List<String> getAllVersion() {
		List<String> versionList = service.findAllEvalVersion();
		return versionList;
	}

	
	/**********************************************
	 * Eval CRUD
	 */
	// CREATE
	// version 1.0 : item=1, item=2
	// parameter -> version=1.0&score=10,20
	@RequestMapping(value="/createEval", method=RequestMethod.POST)
	public void createEval(@RequestParam("version")String version, @RequestParam("score")List<Long> scores) {
		service.createEval(version, scores);
	}
	
	// READ
	// parameter는.. userTaskNo로 찾으면 될듯
	@RequestMapping(value="/findEval", method=RequestMethod.POST)
	public List<Eval> findEval(@RequestParam("userTaskNo")Long userTaskNo) {
		List<Eval> evalList = service.findEval(userTaskNo);
		
		return evalList;
	}
	
	// UPDATE
	@RequestMapping(value="/updateEval", method=RequestMethod.POST)
	public void updateEval(@RequestParam("userTaskNo")Long userTaskNo) {
		service.deleteEval(userTaskNo);
	}	
	
	// DELETE
	@RequestMapping(value="/deleteEval", method=RequestMethod.POST)
	public void deleteEval(@RequestParam("userTaskNo")Long userTaskNo) {
		service.deleteEval(userTaskNo);
	}
	
}
