package me.zhongmin.zero.datastructure.binarytree;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {

	TreeNode left,right,next;
	int val;
	public TreeNode(int val) {
		super();
		this.val = val;
	}
	@Override
	public String toString() {
		return "{\"val\":\"" + val + "\"}";
	}
	
}
