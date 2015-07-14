package service.impl.base;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class BaseMathImpl {
	
	/**
	 * 以List中的key進行排序, 排序標的為String
	 * @param list 排序List(of Map)
	 * @param key Map of String
	 * @param desc 是否為反序
	 * @return
	 */
	public List sortListByKey(List list, final String key, final boolean desc) {
		Collections.sort(list, new Comparator<Map>() {
			public int compare(Map o1, Map o2) {
				Double s1 = Double.parseDouble(o1.get(key).toString());
				Double s2 = Double.parseDouble(o2.get(key).toString());
				if (s1 == null && s2 == null) {
					return 0;
				} else if (s1 == null) {
					return -1;
				} else if (s2 == null) {
					return 1;
				} else {
					if(desc){
						return s2.compareTo(s1);
					}else{
						return s1.compareTo(s2);
						
					}					
				}
			}
		});
		return list;
	}
	
	/**
	 * 以List中的key進行遞增排序
	 * @param list
	 * @param key
	 * @return
	 */
	public List sortListByKey(List list, final String key) {
		Collections.sort(list, new Comparator<Map>() {
            public int compare(Map o1, Map o2) {
            	Double s1 = Double.parseDouble(o1.get(key).toString());
				Double s2 = Double.parseDouble(o2.get(key).toString());
                if (s1 == null && s2 == null) {
                    return 0;
                } else if (s1 == null) {
                    return -1;
                } else if (s2 == null) {
                    return 1;
                } else {
                    return s1.compareTo(s2);
                }
            }
        });
		return list;
	}
	
	/**
	 * 以List中的key進行遞減排序
	 * @param list
	 * @param key
	 * @return
	 */
	public List sortListByKeyDESC(List list, final String key){
		
		Collections.sort(list, new Comparator<Map>() {
            public int compare(Map o1, Map o2) {
            	Double s1 = Double.parseDouble(o1.get(key).toString());
				Double s2 = Double.parseDouble(o2.get(key).toString());
                if (s1 == null && s2 == null) {
                    return 0;
                } else if (s1 == null) {
                    return -1;
                } else if (s2 == null) {
                    return 1;
                } else {
                    return s2.compareTo(s1);
                }
            }
        });
		return list;
	}
	
	
	

}
