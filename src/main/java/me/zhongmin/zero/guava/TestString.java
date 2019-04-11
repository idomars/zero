package me.zhongmin.zero.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Shorts;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestString {

	/**
	 * 字符串连接操作
	 * @Title: testJoiner 
	 * @param 
	 * @date 2018年5月21日 下午5:30:32
	 * @return void 
	 * @throws IOException 
	 * @throws 
	 * @author remin
	 */
	@Test
	public void testJoiner() throws IOException{
		 System.out.println(Joiner.on(",")
		         .skipNulls()
		         .join(Arrays.asList(1,2,3,4,5,null,6)));//忽略掉null值
		 
		 System.out.println(Joiner.on(",")
		         .skipNulls()
		         .join("南京","扬州","淮安","苏州"));//忽略掉null值
		 
		 System.out.println(Joiner.on(",")
				 .useForNull("default")
				 .join(Arrays.asList(1,2,3,4,5,null,6)));//为null提供一个默认值
		 System.out.println(Joiner.on(" ").appendTo(new StringBuilder("result: "),1,2));
		 //System.out.println(Joiner.on(" ").appendTo(writer,1,2));
		 File file = new File(getClass().getResource("joinerAppend.txt").getFile());
		 System.out.println(file.getName());
		 System.out.println(file.getPath());
		 FileWriter writer = new FileWriter(file);
		 Joiner.on(" ").appendTo(writer,1,2);
		 writer.flush();
		 writer.close();
		 
		 Map<String,String> map = new HashMap<String, String>();
		 map.put("key1", "value1");
		 map.put("key2", "value2");
		 map.put("key3", "value3");
		 map.put("key4", "value4");
		 map.put("key5", "value5");
		System.out.println(Joiner.on("&").withKeyValueSeparator("=").join(map));//连接map
		 
	}
	/**
	 * 字符串分隔操作
	 * @Title: TestSplitter 
	 * @param 
	 * @date 2018年5月22日 下午2:39:50
	 * @return void 
	 * @throws 
	 * @author remin
	 */
	@Test
	public void TestSplitter(){
		String test = "a |b|c| |d%e%f#g#f";
		System.out.println(Splitter.on("|").split(test).toString());
		System.out.println(Splitter.on("|").omitEmptyStrings().split(test).toString());//忽略空值
		System.out.println(Splitter.on("|").trimResults().split(test).toString());//忽略空值
		System.out.println(Splitter.on("|").limit(3).split(test).toString());//忽略空值
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void TestCharMatcher(){
		String str = "FirstName LastName +1 123 456 789 !@#$%^&*()_+|}{:\"?><";
		System.out.println(CharMatcher.JAVA_LOWER_CASE.negate().retainFrom(str));
		System.out.println(CharMatcher.DIGIT.retainFrom(str));
		
	}
	
	@Test
	public void testCaseFormat(){
	      String data = "test_data";
	      System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
	      System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
	      System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
	   }
	
	
	@Test
	public void testBytes(){
		      byte[] byteArray = {1,2,3,4,5,5,7,9,9};

		      //convert array of primitives to array of objects
		      List<Byte> objectArray = Bytes.asList(byteArray);
		      System.out.println(objectArray.toString());

		      //convert array of objects to array of primitives
		      byteArray = Bytes.toArray(objectArray);
		      System.out.print("[ ");
		      for(int i = 0; i< byteArray.length ; i++){
		         System.out.print(byteArray[i] + " ");
		      }
		      System.out.println("]");
		      byte data = 5;
		      //check if element is present in the list of primitives or not
		      System.out.println("5 is in list? "+ Bytes.contains(byteArray, data));

		      //Returns the index		
		      System.out.println("Index of 5: " + Bytes.indexOf(byteArray,data));

		      //Returns the last index maximum		
		      System.out.println("Last index of 5: " + Bytes.lastIndexOf(byteArray,data));	
		      
		      System.out.println(Bytes.asList(Bytes.concat(new byte[]{1,2},new byte[]{3,4},new byte[]{1,2})).toString());	
 
	}
	
	@Test
	 public void testShorts(){
	      short[] shortArray = {1,2,3,4,5,6,7,8,9};

	      //convert array of primitives to array of objects
	      List<Short> objectArray = Shorts.asList(shortArray);
	      System.out.println(objectArray.toString());

	      //convert array of objects to array of primitives
	      shortArray = Shorts.toArray(objectArray);
	      System.out.print("[ ");
	      for(int i = 0; i< shortArray.length ; i++){
	         System.out.print(shortArray[i] + " ");
	      }
	      System.out.println("]");
	      short data = 5;
	      //check if element is present in the list of primitives or not
	      System.out.println("5 is in list? "+ Shorts.contains(shortArray, data));

	      //Returns the minimum		
	      System.out.println("Min: " + Shorts.min(shortArray));

	      //Returns the maximum		
	      System.out.println("Max: " + Shorts.max(shortArray));
	      data = 2400;
	      //get the byte array from an integer
	      byte[] byteArray = Shorts.toByteArray(data);
	      for(int i = 0; i< byteArray.length ; i++){
	         System.out.print(byteArray[i] + " ");
	      }
	   }
}
