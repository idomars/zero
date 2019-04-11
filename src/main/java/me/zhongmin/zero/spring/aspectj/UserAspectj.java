package me.zhongmin.zero.spring.aspectj;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class UserAspectj {

	@Pointcut("execution(* me.zhongmin.zero.spring.aspectj.service.*Service.*(..))")
	public void saveUser(){
		
	}
	
	@Before(value = "saveUser()")
	public void savebefore(JoinPoint point){
		log.info(" before 方法 正在保存日志..." );
		try {
			TimeUnit.NANOSECONDS.sleep(100);
			System.out.println("@Before：目标方法为：" + 
	                point.getSignature().getDeclaringTypeName() + 
	                "." + point.getSignature().getName());
	        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
	        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@After("saveUser()")
	public void saveAfter(JoinPoint point){
		System.out.println("@After：模拟释放资源...");
        System.out.println("@After：目标方法为：" + 
                point.getSignature().getDeclaringTypeName() + 
                "." + point.getSignature().getName());
        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
	}
	
	@AfterReturning("saveUser()")
	public void AfterReturnings(){
	   System.out.println("我是后置通知...");
	}
	
	/**
	* 后置通知
	* returnVal,切点方法执行后的返回值
	*/
	@AfterReturning(value="saveUser()",returning = "aa")
	public void AfterReturning2(JoinPoint joinPoint,Object aa){
		System.out.println("我是后置通知 "+joinPoint.toLongString());
	   System.out.println("我是后置通知...returnVal+"+aa);
	}
	
	/**
	* 抛出通知
	* @param e 抛出异常的信息
	*/
	@AfterThrowing(value="saveUser()",throwing = "e")
	public void afterThrowable(Throwable e){
	  System.out.println("出现异常:msg="+e.getMessage());
	}
	
	@Around("saveUser()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
	    System.out.println("我是环绕通知前....");
	    //执行目标函数
	    Object obj= (Object) joinPoint.proceed();
	    System.out.println("我是环绕通知后...."+obj.toString());
	    return obj;
	}
}
