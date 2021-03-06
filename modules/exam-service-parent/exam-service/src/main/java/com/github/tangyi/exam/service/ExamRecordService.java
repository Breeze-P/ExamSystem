package com.github.tangyi.exam.service;

import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.basic.utils.excel.ExcelToolUtil;
import com.github.tangyi.common.basic.vo.DeptVo;
import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.common.core.utils.DateUtils;
import com.github.tangyi.common.core.utils.PageUtil;
import com.github.tangyi.common.core.utils.ResponseUtil;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.exam.api.dto.AnswerDto;
import com.github.tangyi.exam.api.dto.ExaminationDashboardDto;
import com.github.tangyi.exam.api.dto.ExaminationRecordDto;
import com.github.tangyi.exam.api.dto.SubjectDto;
import com.github.tangyi.exam.api.enums.SubmitStatusEnum;
import com.github.tangyi.exam.api.module.Answer;
import com.github.tangyi.exam.api.module.Examination;
import com.github.tangyi.exam.api.module.ExaminationRecord;
import com.github.tangyi.exam.excel.model.ExamRecordExcelModel;
import com.github.tangyi.exam.mapper.AnswerMapper;
import com.github.tangyi.exam.mapper.ExamRecordMapper;
import com.github.tangyi.exam.utils.ExamRecordUtil;
import com.github.tangyi.user.api.feign.UserServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ????????????service
 *
 * @author tangyi
 * @date 2018/11/8 21:20
 */
@Slf4j
@AllArgsConstructor
@Service
public class ExamRecordService extends CrudService<ExamRecordMapper, ExaminationRecord> {

	private final UserServiceClient userServiceClient;

	private final ExaminationService examinationService;

	private final AnswerMapper answerMapper;

	private final SubjectService subjectService;

	/**
     * ??????????????????
     *
     * @param examRecord examRecord
     * @return ExamRecord
     * @author tangyi
     * @date 2019/1/3 14:10
     */
    @Override
    @Cacheable(value = "record#" + CommonConstant.CACHE_EXPIRE, key = "#examRecord.id")
    public ExaminationRecord get(ExaminationRecord examRecord) {
        return super.get(examRecord);
    }

	/**
	 * ??????????????????
	 *
	 * @param pageNum    pageNum
	 * @param pageSize   pageSize
	 * @param sort       sort
	 * @param order      order
	 * @param examRecord examRecord
	 * @return PageInfo
	 * @author tangyi
	 * @date 2018/11/10 21:33
	 */
    public PageInfo<ExaminationRecordDto> examRecordList(ExaminationRecord examRecord, String pageNum, String pageSize, String sort, String order) {
		examRecord.setTenantCode(SysUtil.getTenantCode());
		PageInfo<ExaminationRecordDto> examRecordDtoPageInfo = new PageInfo<>();
		List<ExaminationRecordDto> examRecordDtoList = new ArrayList<>();
		// ??????????????????
		PageInfo<ExaminationRecord> examRecordPageInfo = this.findPage(
				PageUtil.pageInfo(pageNum, pageSize, sort, order), examRecord);
		if (CollectionUtils.isNotEmpty(examRecordPageInfo.getList())) {
			// ??????????????????
			List<Examination> examinations = examinationService.findListById(examRecordPageInfo.getList().stream().map(ExaminationRecord::getExaminationId).distinct().toArray(Long[]::new));
			examRecordPageInfo.getList().forEach(tempExamRecord -> {
				// ???????????????????????????????????????
				Examination examinationRecordExamination = examinations.stream()
						.filter(tempExamination -> tempExamRecord.getExaminationId().equals(tempExamination.getId()))
						.findFirst().orElse(null);
				// ?????????ExamRecordDto
				if (examinationRecordExamination != null) {
					ExaminationRecordDto examRecordDto = new ExaminationRecordDto();
					BeanUtils.copyProperties(examinationRecordExamination, examRecordDto);
					examRecordDto.setId(tempExamRecord.getId());
					examRecordDto.setStartTime(tempExamRecord.getStartTime());
					examRecordDto.setEndTime(tempExamRecord.getEndTime());
					examRecordDto.setScore(tempExamRecord.getScore());
					examRecordDto.setUserId(tempExamRecord.getUserId());
					examRecordDto.setExaminationId(tempExamRecord.getExaminationId());
					// ???????????????
					examRecordDto.setCorrectNumber(tempExamRecord.getCorrectNumber());
					examRecordDto.setInCorrectNumber(tempExamRecord.getInCorrectNumber());
					// ????????????
					examRecordDto.setSubmitStatus(tempExamRecord.getSubmitStatus());
					examRecordDtoList.add(examRecordDto);
				}
			});
			this.fillExamUserInfo(examRecordDtoList, examRecordPageInfo.getList().stream().map(ExaminationRecord::getUserId).distinct().toArray(Long[]::new));
		}
		examRecordDtoPageInfo.setTotal(examRecordPageInfo.getTotal());
		examRecordDtoPageInfo.setPages(examRecordPageInfo.getPages());
		examRecordDtoPageInfo.setPageSize(examRecordPageInfo.getPageSize());
		examRecordDtoPageInfo.setPageNum(examRecordPageInfo.getPageNum());
		examRecordDtoPageInfo.setList(examRecordDtoList);
		return examRecordDtoPageInfo;
	}

    /**
     * ??????????????????
     *
     * @param examRecord examRecord
     * @return ExamRecord
     * @author tangyi
     * @date 2019/1/3 14:10
     */
    @Override
    @Transactional
    @CacheEvict(value = "record", key = "#examRecord.id")
    public int update(ExaminationRecord examRecord) {
        return super.update(examRecord);
    }

    /**
     * ??????????????????
     *
     * @param examRecord examRecord
     * @return ExamRecord
     * @author tangyi
     * @date 2019/1/3 14:10
     */
    @Override
    @Transactional
    @CacheEvict(value = "record", key = "#examRecord.id")
    public int insert(ExaminationRecord examRecord) {
        return super.insert(examRecord);
    }

    /**
     * ????????????id?????????id??????
     *
     * @param examRecord examRecord
     * @return ExamRecord
     * @author tangyi
     * @date 2018/12/26 13:58
     */
    public ExaminationRecord getByUserIdAndExaminationId(ExaminationRecord examRecord) {
        return this.dao.getByUserIdAndExaminationId(examRecord);
    }

    /**
     * ????????????
     *
     * @param ids ids
     * @return int
     * @author tangyi
     * @date 2019/1/3 14:11
     */
    @Override
    @Transactional
    @CacheEvict(value = "record", allEntries = true)
    public int deleteAll(Long[] ids) {
        return super.deleteAll(ids);
    }

	/**
	 * ?????????????????????????????????
	 * @param examRecordDtoList examRecordDtoList
	 * @param userIds userIds
	 */
    public void fillExamUserInfo(List<ExaminationRecordDto> examRecordDtoList, Long[] userIds) {
		// ??????????????????
		ResponseBean<List<UserVo>> returnT = userServiceClient.findUserById(userIds);
		if (ResponseUtil.isSuccess(returnT)) {
			// ??????????????????
			ResponseBean<List<DeptVo>> deptResponseBean = userServiceClient.findDeptById(returnT.getData().stream().map(UserVo::getDeptId).distinct().toArray(Long[]::new));
			if (ResponseUtil.isSuccess(deptResponseBean)) {
				examRecordDtoList.forEach(tempExamRecordDto -> {
					// ???????????????????????????
					UserVo examRecordDtoUserVo = returnT.getData().stream()
							.filter(tempUserVo -> tempExamRecordDto.getUserId().equals(tempUserVo.getId()))
							.findFirst().orElse(null);
					if (examRecordDtoUserVo != null) {
						// ???????????????
						tempExamRecordDto.setUserName(examRecordDtoUserVo.getName());
						// ???????????????????????????
						if (CollectionUtils.isNotEmpty(deptResponseBean.getData())) {
							DeptVo examRecordDtoDeptVo = deptResponseBean.getData().stream()
									// ????????????ID??????
									.filter(tempDept -> tempDept.getId().equals(examRecordDtoUserVo.getDeptId()))
									.findFirst().orElse(null);
							// ??????????????????
							if (examRecordDtoDeptVo != null)
								tempExamRecordDto.setDeptName(examRecordDtoDeptVo.getDeptName());
						}
					}
				});
			}
		}
	}

	/**
	 * ?????????????????????
	 * @param examinationRecord examinationRecord
	 * @return int
	 * @author tangyi
	 * @date 2020/1/31 5:17 ??????
	 */
	public int findExaminationRecordCount(ExaminationRecord examinationRecord) {
		return this.dao.findExaminationRecordCount(examinationRecord);
	}

	/**
	 *
	 * ???????????????????????????????????????
	 * @param start start
	 * @return List
	 * @author tangyi
	 * @date 2020/1/31 10:17 ??????
	 */
	public List<ExaminationRecord> findExaminationRecordCountByDate(Date start) {
		return this.dao.findExaminationRecordCountByDate(start);
	}

	/**
	 * ??????
	 *
	 * @param ids ids
	 * @param request request
	 * @param response response
	 * @author tangyi
	 * @date 2018/12/31 22:28
	 */
	public void exportExamRecord(Long[] ids, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<ExaminationRecord> examRecordList;
			if (ArrayUtils.isNotEmpty(ids)) {
				examRecordList = this.findListById(ids);
			} else {
				// ????????????
				ExaminationRecord examRecord = new ExaminationRecord();
				examRecord.setTenantCode(SysUtil.getTenantCode());
				examRecordList = this.findList(examRecord);
			}
			// ????????????????????????????????????
			if (CollectionUtils.isNotEmpty(examRecordList)) {
				List<ExaminationRecordDto> examRecordDtoList = new ArrayList<>();
				// ??????????????????
				List<Examination> examinations = examinationService.findListById(examRecordList.stream().map(ExaminationRecord::getExaminationId).distinct().toArray(Long[]::new));
				// ??????id
				Set<Long> userIdSet = new HashSet<>();
				examRecordList.forEach(tempExamRecord -> {
					// ???????????????????????????????????????
					Examination examRecordExamination = examinations.stream()
							.filter(tempExamination -> tempExamRecord.getExaminationId().equals(tempExamination.getId()))
							.findFirst().orElse(null);
					if (examRecordExamination != null) {
						ExaminationRecordDto recordDto = new ExaminationRecordDto();
						recordDto.setId(tempExamRecord.getId());
						recordDto.setExaminationName(examRecordExamination.getExaminationName());
						recordDto.setStartTime(tempExamRecord.getStartTime());
						recordDto.setEndTime(tempExamRecord.getEndTime());
						recordDto.setDuration(
								ExamRecordUtil.getExamDuration(tempExamRecord.getStartTime(), tempExamRecord.getEndTime()));
						recordDto.setScore(tempExamRecord.getScore());
						recordDto.setUserId(tempExamRecord.getUserId());
						recordDto.setCorrectNumber(tempExamRecord.getCorrectNumber());
						recordDto.setInCorrectNumber(tempExamRecord.getInCorrectNumber());
						recordDto.setSubmitStatusName(
								SubmitStatusEnum.match(tempExamRecord.getSubmitStatus(), SubmitStatusEnum.NOT_SUBMITTED).getName());
						userIdSet.add(tempExamRecord.getUserId());
						examRecordDtoList.add(recordDto);
					}
				});
				this.fillExamUserInfo(examRecordDtoList, userIdSet.toArray(new Long[0]));
				ExcelToolUtil.writeExcel(request, response, ExamRecordUtil.convertToExcelModel(examRecordDtoList), ExamRecordExcelModel.class);
			}
		} catch (Exception e) {
			log.error("Export examRecord failed", e);
		}
	}

	/**
	 * ????????????????????????
	 *
	 * @return ExaminationDashboardDto
	 * @author tangyi
	 * @date 2019/10/27 20:07:38
	 */
	public ExaminationDashboardDto findExamDashboardData(String tenantCode) {
		ExaminationDashboardDto dashboardDto = new ExaminationDashboardDto();
		Examination examination = new Examination();
		examination.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), tenantCode);
		// ????????????
		dashboardDto.setExaminationCount(examinationService.findExaminationCount(examination));
		// ????????????
		dashboardDto.setExamUserCount(examinationService.findExamUserCount(examination));
		// ??????????????????
		ExaminationRecord examinationRecord = new ExaminationRecord();
		examinationRecord.setCommonValue(examination.getCreator(), examination.getApplicationCode(), examination.getTenantCode());
		dashboardDto.setExaminationRecordCount(this.findExaminationRecordCount(examinationRecord));
		return dashboardDto;
	}

	/**
	 * ????????????n????????????????????????
	 * @param tenantCode tenantCode
	 * @param pastDays pastDays
	 * @return ExaminationDashboardDto
	 * @author tangyi
	 * @date 2020/1/31 5:46 ??????
	 */
	public ExaminationDashboardDto findExamRecordTendency(String tenantCode, int pastDays) {
		ExaminationDashboardDto dashboardDto = new ExaminationDashboardDto();
		Examination examination = new Examination();
		examination.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), tenantCode);
		Map<String, String> tendencyMap = new LinkedHashMap<>();
		LocalDateTime start = null;
		pastDays = -pastDays;
		for (int i = pastDays; i <= 0; i++) {
			LocalDateTime localDateTime = DateUtils.plusDay(i);
			if (i == pastDays) {
				start = localDateTime;
			}
			tendencyMap.put(localDateTime.format(DateUtils.FORMATTER_DAY), "0");
		}
		List<ExaminationRecord> examinationRecords = this.findExaminationRecordCountByDate(DateUtils.asDate(start));
		if (CollectionUtils.isNotEmpty(examinationRecords)) {
			Map<String, List<ExaminationRecord>> examinationRecordsMap = examinationRecords.stream()
					.peek(examinationRecord -> examinationRecord
							.setExt(DateUtils.asLocalDateTime(examinationRecord.getCreateDate())
									.format(DateUtils.FORMATTER_DAY)))
					.collect(Collectors.groupingBy(ExaminationRecord::getExt));
			log.info("ExamRecordTendency map: {}", examinationRecordsMap);
			examinationRecordsMap.forEach((key, value) -> tendencyMap.replace(key, String.valueOf(value.size())));
		}
		dashboardDto.setExamRecordDate(new ArrayList<>(tendencyMap.keySet()));
		dashboardDto.setExamRecordData(new ArrayList<>(tendencyMap.values()));
		return dashboardDto;
	}

	/**
	 * ????????????
	 * @param id id
	 * @return ExaminationRecordDto
	 * @author tangyi
	 * @date 2020/2/21 9:26 ??????
	 */
	public ExaminationRecordDto details(Long id) {
		ExaminationRecord examRecord = this.get(id);
		if (examRecord == null)
			throw new CommonException("ExamRecord is not exist");
		Examination examination = examinationService.get(examRecord.getExaminationId());
		if (examination == null)
			throw new CommonException("Examination is not exist");
		ExaminationRecordDto examRecordDto = new ExaminationRecordDto();
		BeanUtils.copyProperties(examination, examRecordDto);
		examRecordDto.setId(examRecord.getId());
		examRecordDto.setStartTime(examRecord.getStartTime());
		examRecordDto.setEndTime(examRecord.getEndTime());
		examRecordDto.setScore(examRecord.getScore());
		examRecordDto.setUserId(examRecord.getUserId());
		examRecordDto.setExaminationId(examRecord.getExaminationId());
		examRecordDto.setDuration(
				ExamRecordUtil.getExamDuration(examRecord.getStartTime(), examRecord.getEndTime()));
		// ???????????????
		examRecordDto.setCorrectNumber(examRecord.getCorrectNumber());
		examRecordDto.setInCorrectNumber(examRecord.getInCorrectNumber());
		// ????????????
		examRecordDto.setSubmitStatus(examRecord.getSubmitStatus());
		// ????????????
		List<Answer> answers = answerMapper.findListByExamRecordId(examRecord.getId());
		if (CollectionUtils.isNotEmpty(answers)) {
			List<AnswerDto> answerDtos = answers.stream().map(answer -> {
				AnswerDto answerDto = new AnswerDto();
				BeanUtils.copyProperties(answer, answerDto);
				SubjectDto subjectDto = subjectService.get(answer.getSubjectId(), answer.getType());
				answerDto.setSubject(subjectDto);
				answerDto.setDuration(ExamRecordUtil.getExamDuration(answer.getStartTime(), answer.getEndTime()));
				return answerDto;
			}).collect(Collectors.toList());
			examRecordDto.setAnswers(answerDtos);
		}
		this.fillExamUserInfo(Collections.singletonList(examRecordDto), new Long[] {examRecord.getUserId()});
		return examRecordDto;
	}
}
