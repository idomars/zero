package me.zhongmin.zero.guava;


import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestOptional {

	/**
	 * 更友好的处理null
	 * @Title: testOptional 
	 * @param 
	 * @date 2018年5月21日 下午2:31:49
	 * @return void 
	 * @throws 
	 * @author remin
	 */
	@Test
	public void testOptional(){
		  Integer value1 =  null;
	      Integer value2 =  new Integer(10);
	      //Optional.fromNullable - 允许参数为null.a为引用
	      Optional<Integer> a = Optional.fromNullable(value1);
	      Optional<Integer> b = Optional.of(value2);//不允许参数为null,否则抛出NullPointException异常	
	      Optional<Integer> c = Optional.absent();//创建一个新的
	      System.out.println("a=====" + a.orNull());//a.orNull() 返回optional包含的引用 如果引用缺失则返回null
	      System.out.println("b=====" + b.get());//a.get() 返回optional包含的引用 如果引用缺失则抛出IllegalStateException异常
	      System.out.println("c====" + c.or(0));//a.or(T) 返回optional包含的引用 如果引用缺失则返回默认值
	     
	      if(b.isPresent()){//判断b是否引用缺失
	    	  System.out.println(b.get());
	      }else{
	    	  System.out.println(b.orNull());
	      }
	}
	
	/**
	 * 校验工具类 
	 * @Title: testPreconditions 
	 * @param 
	 * @date 2018年5月21日 下午2:31:40
	 * @return void 
	 * @throws 
	 * @author remin
	 */
	@Test
	public void testPreconditions(){
		String a = "1";
		Preconditions.checkArgument("1".equals(a));//如果不成立则抛出IllegalArgumentException异常
		Preconditions.checkArgument("1".equals(a),"我是提示消息");//如果不成立则抛出IllegalArgumentException异常 错误消息为传入的消息
		Preconditions.checkArgument("1".equals(a), "我是提示%s消%s息", 1,2,3,4);//
		Preconditions.checkNotNull("","xxx不可为空");//校验参数不可为null
		int[] arr = new int[11];
		Preconditions.checkElementIndex(9,arr.length);
		Preconditions.checkElementIndex(10,arr.length,"数组越界");
		Preconditions.checkElementIndex(10,arr.length,"数组越界 %s %s");
		
		Preconditions.checkPositionIndex(11, arr.length);//
		
		Preconditions.checkState(false);//如果不成立抛出非法状态异常 IllegalStateException
		
	}
	
	
	@Test
	public void testOrdering(){
		  List<Integer> listtest= new ArrayList<>();
	        listtest.add(3);
	        listtest.add(2);
	        listtest.add(1);
	        listtest.add(4);
	        
		Ordering<Integer> intorder = Ordering.natural();
		
		
		List<Integer> sortlist = intorder.reverse().sortedCopy(listtest);
		int max = intorder.max(listtest);
		System.out.println(sortlist);
		System.out.println(max);
		System.out.println(intorder.leastOf(listtest, 1));
		
		System.out.println("listtest:"+ listtest);
        // 判断是否已经排序
        System.out.println(intorder.isOrdered(sortlist));
        // 是否严格有序
        System.out.println(intorder.isStrictlyOrdered(sortlist));
		
	}
	
	
	@Test
	public void testRange(){
		 Range<Integer> range1 = Range.closed(1, 9);	
	      System.out.print(" ");
	      printRange(range1);		
		
	}
	
  private void printRange(Range<Integer> range){		
      System.out.print("[ ");
      for(int grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
         System.out.print(grade +" ");
      }
      System.out.println("]");
   }
}
