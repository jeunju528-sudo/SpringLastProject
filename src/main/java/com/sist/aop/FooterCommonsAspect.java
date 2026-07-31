package com.sist.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.service.FoodService;
import com.sist.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class FooterCommonsAspect {

	private final FoodService foodService;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void sendData() {
		// 현재 사용중인 request를 얻어 온다
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<FoodVO> fList = foodService.foodHit7Data();
		request.setAttribute("fList", fList);
	}
	
	@Around("execution(* com.sist.web.*Controller.*(..))")
	public Object log(ProceedingJoinPoint jp) throws Throwable {
		Object obj = null;
		System.out.println("사용자 요청: "+jp.getSignature().getName());
		obj = jp.proceed(); // 함수 요청
		System.out.println("사용자 요청완료: "+jp.getSignature().getName());
		return obj;
	}
}
