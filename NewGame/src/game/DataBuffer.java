package game;

import java.util.ArrayList;

public class DataBuffer<T> {
	
	private T data;
	public T data2;
	
	boolean hasBeenSet;
	
	public DataBuffer(T data, ArrayList<DataBuffer> bufferList){
		this.data = data;
		this.data2 = data;
		this.hasBeenSet = false;
		bufferList.add(this);
	}
	
	public T get(){
		return data;
	}
	
	public boolean set (T data){
//		if(hasBeenSet){
//			try {
//				String errorMsg = "DataBuffer has already been set";
//				throw  new Exception(errorMsg);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.exit(8);
//			}
//			return false;
//			
//		}
		this.data2 = data;
		hasBeenSet = true;
		return true;
	}
	
	public void transfer(){
		data = data2;
		hasBeenSet = false;
	}
}
