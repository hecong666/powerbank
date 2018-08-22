package org.bicyclesharing.util;

/**
 * 
 * @author ΢Ц
 * @Controller ����json����Ĺ�����
 */
public class WzwResultUtil {
	
	/**
	 * ˽�л�������
	 */
	private WzwResultUtil(){
		
	}
	
	private int code;
	
	private String msg;
	
	private Object obj;
	
	public WzwResultUtil(int code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	public WzwResultUtil(Object obj) {
		super();
		this.obj = obj;
	}
	
	public static WzwResultUtil buildIsOk(Object obj){
		return new WzwResultUtil(200,"ok",obj);
	}
	
	public static WzwResultUtil buildIsFail(Object obj){
		return new WzwResultUtil(-1,"fail",obj);
	}
	
	public static WzwResultUtil build(int code, String msg, Object obj){
		return new WzwResultUtil(code,msg,obj);
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
