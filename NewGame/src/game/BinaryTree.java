package game;

import java.util.LinkedList;


public class BinaryTree<T extends Comparable<T>>{
 
      public Node startingNode; 
      
      public final boolean LEFT = true;
      public final boolean RIGHT = false;
    
      public int count = 0;
      public T[] array;
     
      public BinaryTree(){

      }
      
      public class Node{
           
            public T data;
            
            public Node left;
            public Node right;
           
            public Node(T data){
                  this.data =  data;
            }
      }
      /**
       * 
       * @param n The node to attempt an insert on.
       * @param direction 
       * @param value
       */
      public void insert(Node n,boolean direction,T value){
            if(direction == LEFT){
                  if(n.left==null){
                        n.left = new Node(value);
                  }
                  else{
                        if(n.left.data.compareTo(value)< 0){
                              insert(n.left, RIGHT ,value);
                        }else{
                              insert(n.left,LEFT,value);
                        }
                  }                
            }
            else if(direction == RIGHT){
                  if(n.right==null){
                        n.right = new Node(value);
                  }
                  else{
                        if(n.right.data.compareTo(value)< 0){
                              insert(n.right, RIGHT ,value);
                        }else{
                              insert(n.right,LEFT,value);
                        }
                  }
            }
      }
     
      public void insert(T value){
            if(startingNode == null){
                  startingNode = new Node(value);
            }
            else{
                  if(startingNode.data.compareTo(value)< 0){
                        insert(startingNode,RIGHT, value);
                  }
                  else{
                        insert(startingNode,LEFT,value);
                  }
            }
      }
     
      public void traverse(Node n){
    	  if(n.left !=null){
    		  traverse(n.left);
    	  }
    	  array[count]=n.data;
    	  count++;
    	  if(n.right !=null){
    		  traverse(n.right);
    	  }
      }
      
      public void traverse(){
    	  traverse(startingNode);
    	  System.out.println("count for tree traversal was " + count);
      }
      
      public T[] sort(T[] inputArray){
    	  array = inputArray;
    	  for(int x = 0; x <array.length; x++){
              insert(array[x]);
         }
    	  traverse();
    	  return array;
      }
     

}
