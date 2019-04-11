package me.zhongmin.zero.guava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import org.junit.Test;

public class TestCollections {
	/**
	 * 重复set 会统计set中每个元素的个数
	 * @Title: testMutiSet 
	 * @param 
	 * @date 2018年5月21日 下午5:28:10
	 * @return void 
	 * @throws 
	 * @author remin
	 */
	@Test
	public void testMutiSet(){
	      Multiset<String> multiset = HashMultiset.create();
	      multiset.add("a");
	      multiset.add("b");
	      multiset.add("c");
	      multiset.add("d");
	      multiset.add("a");
	      multiset.add("b");
	      multiset.add("c");
	      multiset.add("b");
	      multiset.add("b");
	      multiset.add("b");
	      System.out.println(multiset);
	      System.out.println("Total Size : "+multiset.size());
	      Set<String> set = multiset.elementSet();
	      System.out.println(set);
	      System.out.println("Occurrence of 'b' : "+multiset.count("b"));
	      System.out.println("Occurrence of 'd' : "+multiset.count("d"));
	      System.out.println("Occurrence of 'e' : "+multiset.count("e"));
	      
	      System.out.println("MultiSet [");
	      for (Multiset.Entry<String> entry : multiset.entrySet())
	      {
	         System.out.println("Element: "+entry.getElement() +", Occurrence(s): " + entry.getCount());		    
	      }
	      System.out.println("]");	
	      //multiset.remove("b",6);
	      multiset.remove("b");
	    
	      //multiset.setCount("b", 10);

	      //print the occurrence of an element
	      System.out.println("Occurence of 'b' : "+multiset.count("b"));
	}
	
	/**
	 * 双向可逆map,value不可重复
	 * @Title: testBiMap 
	 * @param 
	 * @date 2018年5月21日 下午5:27:49
	 * @return void 
	 * @throws 
	 * @author remin
	 */
	@Test
	public void testBiMap(){
		  BiMap<Integer, String> empIDNameMap = HashBiMap.create();

	      empIDNameMap.put(new Integer(101), "Mahesh");
	      empIDNameMap.put(new Integer(102), "Sohan");
	      empIDNameMap.put(new Integer(103), "Ramesh");
	      empIDNameMap.put(new Integer(101), "Mahesh1");
	      
	      //Emp Id of Employee "Mahesh"
	      System.out.println(empIDNameMap.inverse().get("Mahesh"));
	      System.out.println(empIDNameMap.get(101));
	}
	
	/**
	 * 类型excel的数据结构 row + column = value
	 * @Title: testTabble 
	 * @param 
	 * @date 2018年5月21日 下午5:27:15
	 * @return void 
	 * @throws 
	 * @author remin
	 */
	@Test
	public void testTabble(){
		  //Table<R,C,V> == Map<R,Map<C,V>>
	      /*
	      *  Company: IBM, Microsoft, TCS
	      *  IBM 		-> {101:Mahesh, 102:Ramesh, 103:Suresh}
	      *  Microsoft 	-> {101:Sohan, 102:Mohan, 103:Rohan } 
	      *  TCS 		-> {101:Ram, 102: Shyam, 103: Sunil } 
	      * 
	      * */
	      //create a table
	      Table<String, String, String> employeeTable = HashBasedTable.create();

	      //initialize the table with employee details
	      employeeTable.put("IBM", "101","Mahesh");
	      employeeTable.put("IBM", "102","Ramesh");
	      employeeTable.put("IBM", "103","Suresh");
	      employeeTable.put("APPLE", "101","minjj");

	      employeeTable.put("Microsoft", "101","Sohan");
	      employeeTable.put("Microsoft", "112","Mahesh");
	      employeeTable.put("Microsoft", "113","Rohan");

	      employeeTable.put("TCS", "101","Ram");
	      employeeTable.put("TCS", "122","Shyam");
	      employeeTable.put("TCS", "123","Mahesh");
	      System.out.println(employeeTable);
	      //get Map corresponding to IBM
	      Map<String,String> ibmEmployees =  employeeTable.row("IBM");

	      System.out.println("List of IBM Employees");
	      for(Map.Entry<String, String> entry : ibmEmployees.entrySet()){
	         System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
	      }

	      //get all the unique keys of the table
	      Set<String> employers = employeeTable.rowKeySet();
	      System.out.print("Employers: ");
	      for(String employer: employers){
	         System.out.print(employer + " ");
	      }
	      System.out.println();

	      //get a Map corresponding to 102
	      Map<String,String> EmployerMap =  employeeTable.column("102");
	      for(Map.Entry<String, String> entry : EmployerMap.entrySet()){
	         System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
	      }		
	      //返回集合中的所有行键/列键/值三元组。
	      Set<Table.Cell<String,String,String>> set = employeeTable.cellSet();
	      System.out.println(set);
	      for(Cell<String,String,String> cell :set){
	    	  System.out.println("rowkey="+cell.getRowKey());
	    	  System.out.println("columnkey="+cell.getColumnKey());
	    	  System.out.println("value="+cell.getValue());
	    	  System.out.println(cell);
	      }
	      //返回一组具有表中的一个或多个值的列键。
	      Set<String> columnset = employeeTable.columnKeySet();
	      Map<String,Map<String,String>> columnMap = employeeTable.columnMap();
	      Map<String,String> cmap = columnMap.get("101");
	      System.out.println(cmap);
	      
	      Collection<String> values =  employeeTable.values();
	      System.out.println(values);
	}
	
	
	ImmutableSet set = ImmutableSet.of(1,2,3,4,5,6,6);
	
	
	 private Multimap<String,String> getMultimap(){
	      //Map<String, List<String>>
	      // lower -> a, b, c, d, e 
	      // upper -> A, B, C, D

	      Multimap<String,String> multimap = ArrayListMultimap.create();//允许多

	      multimap.put("lower", "a");
	      multimap.put("lower", "b");
	      multimap.put("lower", "c");
	      multimap.put("lower", "d");
	      multimap.put("lower", "e");

	      multimap.put("upper", "A");
	      multimap.put("upper", "B");
	      multimap.put("upper", "C");
	      multimap.put("upper", "D");		
	      return multimap;		
	   }
	
	
	 @Test
	 public void testMutiMap(){
		  Multimap<String,String> multimap = getMultimap();
		  System.out.println(multimap);
	      List<String> lowerList = (List<String>)multimap.get("lower");
	      System.out.println("Initial lower case list");
	      System.out.println(lowerList.toString());
	      lowerList.add("f");
	      System.out.println("Modified lower case list");
	      System.out.println(lowerList.toString());

	      List<String> upperList = (List<String>)multimap.get("upper");
	      System.out.println("Initial upper case list");
	      System.out.println(upperList.toString());
	      upperList.remove("D");
	      System.out.println("Modified upper case list");
	      System.out.println(upperList.toString());

	      Map<String, Collection<String>> map = multimap.asMap();
	      System.out.println("Multimap as a map");
	      for (Map.Entry<String,  Collection<String>> entry : map.entrySet()) {
	         String key = entry.getKey();
	         Collection<String> value =  multimap.get("lower");
	         System.out.println(key + ":" + value);
	      }

	      System.out.println("Keys of Multimap");
	      Set<String> keys =  multimap.keySet();
	      for(String key:keys){
	         System.out.println(key);
	      }

	      System.out.println("Values of Multimap");
	      Collection<String> values = multimap.values();
	      System.out.println(values);
	 }
	
	
	@Test
	public void testUnModifyCollections(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		System.out.println("list = "+list);
		ImmutableList<Integer> imlist=ImmutableList.copyOf(list);
		System.out.println("imlist = "+imlist);
		 

	}
	
	
	
	
	
}
