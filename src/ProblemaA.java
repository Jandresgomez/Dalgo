//David Felipe Nino Romero 				201412734
//Maria del Rosario Leon				201423755

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProblemaA {
	
	public static void close(BufferedReader br) {
		try {
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int calculate(int[] nums, int c) {
		int max = 0;
		int[] list = new int[nums.length];
		
		for(int i = 0; i < nums.length; i++) {
			for(int j = i; j < nums.length; j++) {
				if(i == j) {
					list[j] = (nums[j] == 0? 1 : 0);
				} else {
					list[j] = list[j - 1] + (nums[j] == 0? 1 : 0);
				}
				
				if(list[j] > c) break;
				
				int lenght = (j - i + 1);
				if(lenght > max) {
					max = lenght;
				}
			}
			System.out.println(Arrays.toString(list));
		}
		
		return max;
	}
	
	public static int[] toNumArray(String[] split) {
		int[] nums = new int[split.length - 2];
		for(int i = 0; i < nums.length; i++) nums[i] = Integer.parseInt(split[i + 2]);
		return nums;
	}
	
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			while(line != null) {
				String[] split = line.split(" ");
				int c = Integer.parseInt(split[1]);
				int[] nums = toNumArray(split);
				int k = calculate(nums, c);
				System.out.println(k);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(br != null) {
				close(br);
			}
		}
	}
}
