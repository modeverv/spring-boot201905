package com.example.demo.trySpring.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	@Before("execution(* *..*.*Controller.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("メソッドの開始:" + jp.getSignature());
	}
	
	@After("execution(* *..*.*Controller.*(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("メソッド終了:" + jp.getSignature());
	}

	@Around("execution(* *..*.*.*Controller.*(..))")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("アラウント開始:" + jp.getSignature());
		try {
			Object result = jp.proceed();
			System.out.println("アラウント終了:" + jp.getSignature());
			return result;
		} catch (Exception e) {
			System.out.println("メソッド異常終了:" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

	// beanでPointcutを指定
	@Around("bean(*Controller)")
	public Object beanLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("Beanアラウント開始:" + jp.getSignature());
		try {
			Object result = jp.proceed();
			System.out.println("Beanアラウント終了:" + jp.getSignature());
			return result;
		} catch (Exception e) {
			System.out.println("Beanメソッド異常終了:" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

	// アノテーションでpointcutを指定
	@Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void getMethodLog(JoinPoint jp) {
		System.out.println("メソッドの開始:" + jp.getSignature());
	}
	// AOPの用語
	// Advice 処理内容
	// Pointcut 実行場所
	// JoinPoint 実行タイミング
}
