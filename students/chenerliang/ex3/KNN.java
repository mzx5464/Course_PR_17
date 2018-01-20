import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KNN {
	//KNN�Ĺؼ�����K
	public static int K = 3;
	
	
	public static void main(String args[]){
/*		 //Ԥ����ȷ�����ָ���
		 double correctNum = 0;
		 //Ԥ����������ָ���
		 double allNum = 0;
		 //Ԥ����Ƿ���ȷ��ʶ
		 boolean flag = false;
		 
		 for(int i= 0;i<10;i++ ){//��0~9��10�����ֽ���Ԥ��
			 for(int j=0;j<40;j++){//�ֱ�ѡȡ���Լ��е�40���������в���
				 //����Ԥ��
				 int back = predict("C:/Users/hp/Desktop/KNN/���Լ�/"+i+"_"+j+".txt");
				 //Ԥ�����ֵĸ�����1
				 allNum++;
				 //���Ԥ����ȷ����Ԥ����ȷ������1��flag��Ϊtrue
				 if(back == i){
					 correctNum++;
					 flag = true;
				 }
				//������
				 if(flag){
					 System.out.println("Ԥ����ȷ��"+"��ʵֵ��"+i+"��Ԥ��ֵ��"+back);
					 flag = false;
				 }
				 else{
					 System.out.println("Ԥ�����"+"��ʵֵ��"+i+"��Ԥ��ֵ��"+back); 
				 }
			 }
		 }
		 
		 System.out.println("���Լ�������"+allNum+"����ȷ������"+correctNum);
		 System.out.println("׼ȷ�ʣ�"+correctNum/allNum);
*/
		
		int predict =  predict("C:/Users/hp/Desktop/KNN/��д�ַ���/"+"a.txt");
		System.out.println("Ԥ��������ǣ�"+predict);
	}

	//�����ı��ж�����32*32��0,1���ŵ�һ��������
	private static int[] dataToVector(String fileName){
		//����һ��32*32������������Ŷ����0,1��
		int array[] = new int[32*32];
		//��ȡ�ļ����ݲ����в���
		try{
            FileReader reader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(reader);
            //���ļ���32��32�зֱ���ж�ȡ
            for(int row=0; row<32; row++){
                String str = buffer.readLine();
                for(int col=0; col<32; col++){
                    String c = "" + str.charAt(col);
                    array[32*row+col] = Integer.parseInt(c);
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return array;
	}
	
	//��������֮���ŷʽ����
	public static double computeDistance(int aVector[],int bVector[]){
		//��������ս��
		double distance = 0;
		//�����ƽ��
		int distance2 = 0;
		//����distance2
		for(int i=0;i<32*32;i++){
			int difference = aVector[i]-bVector[i];
			distance2 = distance2 + difference*difference;
		}
		//������ھ����ƽ��������
		distance = Math.sqrt(distance2);	
		return distance;
	}
	
	private static int predict(String fileName) {
		//Ԥ��ֵ
		int back=-1;
		//���ҪԤ���32*32����
		int aVector[] = dataToVector(fileName);
		//��ҪԤ��ĵ������K���㼰���ǵľ���
		double distanceArray[] = new double[K];
		int numArray[] = new int[K];
		//��ʼ��
		for(int i=0;i<K;i++){
			distanceArray[i]=32;
			numArray[i]=-1;
		}
		//��������㼰�����
		 for(int i = 0; i <= 9; i++){
	            for(int j = 0; j < 100; j++){
	                int bVector[] = dataToVector("C:/Users/hp/Desktop/KNN/ѵ����/"+i+"_"+j+".txt");
	                double distance = computeDistance(aVector, bVector);

	                for(int k = 0; k < K; k++){
	                    if(distance < distanceArray[k]){
	                    	for(int h=k;h<K-1;h++){
	                    		distanceArray[h+1] = distanceArray[h];
	                    		numArray[h+1] = numArray[h];
	                    	}	                    	
	                        distanceArray[k] = distance;
	                        numArray[k] = i;
	                        break;
	                    }
	                }
	            }
	        }
		//ͳ��������в�ͬ���ֵĸ���������i�ĸ�������count[i]��
		int count[] = new int[10];
		//count[]��ʼ��
		for(int i=0;i<10;i++){
			count[i]=0;
		}
		//ͳ��
		for(int i=0;i<K;i++){
			if(numArray[i]!=-1){
				count[numArray[i]]++;
			}
		}
		//���������������i
		int max=0;
		for(int i=0;i<10;i++){
			if(count[i]>max){
				max = count[i];
				back = i;
			}
		}
		
		return back;
	}

	
}