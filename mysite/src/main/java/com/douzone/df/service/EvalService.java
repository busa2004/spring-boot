package com.douzone.df.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.df.model.Eval;
import com.douzone.df.model.EvalItem;
import com.douzone.df.model.EvalItemScore;
import com.douzone.df.model.EvalItemVersion;
import com.douzone.df.payload.EvalVersionRequest;
import com.douzone.df.repository.EvalItemRepository;
import com.douzone.df.repository.EvalItemScoreReopsitory;
import com.douzone.df.repository.EvalItemVersionRepository;
import com.douzone.df.repository.EvalRepository;



@Service
public class EvalService {

	@Autowired
	private EvalRepository evalRepo;
	@Autowired
	private EvalItemRepository itemRepo;
	@Autowired
	private EvalItemScoreReopsitory scoreRepo;
	@Autowired
	private EvalItemVersionRepository versionRepo;

	/**********************************************
	 * EvalItem CRD
	 */

	// CREATE
	public boolean createEvalItem(EvalItem evalItem) {
		itemRepo.save(evalItem); // insert작업..

		Long id = evalItem.getItemNo();
		boolean result = itemRepo.existsById(id);

		return result;
	}

	// READ
	public EvalItem findItem(EvalItem evalItem) {
		Long id = evalItem.getItemNo();
		EvalItem findItem = itemRepo.findById(id).orElse(null);
		return findItem;
	}

	// DELETE
	public boolean deleteEvalItem(EvalItem findItem) {
		Long id = findItem.getItemNo();
		itemRepo.deleteById(id);
		boolean result = itemRepo.existsById(id);

		return result; // false가 반환되면 정상삭제.
	}

	/**********************************************
	 * EvalItemScore
	 */
	// CREATE
	public boolean createEvalItemScore(EvalItemScore evalItemScore) {
		EvalItem item = evalItemScore.getEvalItem(); // url parameter에서 item no값을 가지고 아이템 찾기
		evalItemScore.setEvalItem(item); // url에서 받아온 값을 왜 다시 setter해줘야 하는가..?

		scoreRepo.save(evalItemScore);

		Long id = evalItemScore.getItemScoreNo();
		boolean result = scoreRepo.existsById(id);

		return result;
	}

	// READ
	public EvalItemScore findItemScore(EvalItemScore evalItemScore) {
		Long id = evalItemScore.getItemScoreNo();

		EvalItemScore findItemScore = scoreRepo.findById(id).orElse(null);

		return findItemScore;
	}

	// UPDATE
	public EvalItemScore updateEvalItemScore(EvalItemScore findItemScore, Long score) {

		// 점수 수정
		findItemScore.setScore(score);
		scoreRepo.save(findItemScore);

		return findItemScore;
	}

	// DELETE
	public boolean deleteEvalItemScore(EvalItemScore findItemScore) {
		Long id = findItemScore.getItemScoreNo();
		scoreRepo.deleteById(id);
		boolean result = scoreRepo.existsById(id);

		return result; // false가 반환되면 정상삭제.
	}

	/**********************************************
	 * EvalItemVersion CR
	 */
	// CREATE
	public boolean createEvalVersion(EvalVersionRequest request) {
		boolean result = false;
		
		String version = request.getVersion();
		List<EvalItem> items = request.getDataSource();
		
		// Create Eval Item
		for(EvalItem item : items) {
			item.setItemNo(null);
			result = createEvalItem(item);
			if(result == false) {
				// 실패했을 때 돌아감..
				return result;
			}
		}	
//		for(int i=0; i<items.size(); i++) {
//			EvalItem item = new EvalItem();
//			
//			item = items.get(i);
//			result = createEvalItem(item);
//			
//			if(result == false) {
//				// 실패했을 때 돌아감..
//				return result;
//			}
//		}
		result = false;

		for (int i = 0; i < items.size(); i++) {
			// 여기서 객체 생성해준 이유 : 같은 객체에서 setter작업만 일어나면 값이 변동되는줄 알고 update 가 일어난다.
			EvalItemVersion evalVersion = new EvalItemVersion();
			
			Long itemNo = items.get(i).getItemNo();
			EvalItem itemObj = itemRepo.findById(itemNo).orElse(null);
			System.out.println(itemObj);

			evalVersion.setVersion(version);
			evalVersion.setEvalItem(itemObj);

			versionRepo.saveAndFlush(evalVersion);

			// Test..
			// 정상저장되었는지 확인
			Long id = evalVersion.getItemVersionNo();
			System.out.println("====" + id);
			result = versionRepo.existsById(id);

			if (result == false) {
				// 저장 실패
				break;
			}
		}
		return result;
	}

	// READ
	public List<EvalItemVersion> findEvalVersion(String version) {
		List<EvalItemVersion> findVersion = versionRepo.findByVersion(version);
		return findVersion;
	}

	// READ
	public List<EvalItem> findEvalItemByVersion(String version) {
		List<EvalItem> findVersion = versionRepo.findEvalItemByVersion(version);
		return findVersion;
	}
	
	// READ All
	public List<String> findAllEvalVersion() {
		List<String> versionList = versionRepo.findAllVersionName();
		
		return versionList;
	}
	
	/**********************************************
	 * Eval CRUD
	 */
	// CREATE
	public void createEval(String version, List<Long> scores) { // return값 boolean으로 바꾸기

		List<EvalItemVersion> versionList = findEvalVersion(version);

		List<Eval> evalList = new ArrayList<Eval>();

		for (int i = 0; i < versionList.size(); i++) {
			EvalItemVersion versionItem = versionList.get(i);
			//Long versionNo = versionItem.getItemVersionNo();
			EvalItem evalItem = versionItem.getEvalItem();

			EvalItemScore itemScore = new EvalItemScore(); // tmp
			itemScore.setScore(scores.get(i));
			itemScore.setEvalItem(evalItem);

			// eval_item_score Table에 저장..
			createEvalItemScore(itemScore); // 정상저장 : true / 실패 false

			// score 테이블에 저장과 동시에 eval에도 저장해야함
			Eval eval = new Eval();
			eval.setEvalItemScoreno(itemScore);
			eval.setEvalItemVersionNo(versionItem);

			evalRepo.save(eval);
		}
	}

	// READ
	public List<Eval> findEval(Long userTaskNo) {
		// findAll 말고 userTaskNo로 찾기..
		List<Eval> evalList = evalRepo.findAll();

		return evalList;
	}
	
	// UPDATE
	public void updateEval(Long userTaskNo) {
		
	}
	
	// DELETE
	public void deleteEval(Long userTaskNo) {
		// userTaskNo의 no찾아서 삭제..
	}
	
}
