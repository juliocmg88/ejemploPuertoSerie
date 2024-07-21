/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance;

/**
 *
 * @author MUNOZ
 */
public class BasePersistenceUtil {

    	public static String wrapStringField(Object field){
		if(field==null){
			return "NULL";
		}else if(field.equals("")){
			return "DEFAULT";
		}else{
			return "'"+field+"'";
		}
	}

}
