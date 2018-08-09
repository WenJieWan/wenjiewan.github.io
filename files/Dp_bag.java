package data;

public class Dp_bag {

	public static void main(String[] args) {
		int n=5;//��Ʒ���� 
		int c=6;//��������
		int[] w=new int[]{0,3,2,1,4,5};//��ֵ��ʼ������
		int[] v=new int[]{0,25,20,15,40,50};//��ֵ��ʼ����ֵ
		int[][] maxV=new int[n+1][c+1];//���
		int[][] d=new int[n+1][c+1];//��¼��������
		int[] q=new int[n+1];//�������Ž�
		//����������м���
		Dp_bag dp_bag=new Dp_bag();
		dp_bag.bag(n,c,w,v,maxV,d,q);
	}
	
	public void bag(int n,int c,int[] w,int[] v,int[][] maxV,int[][] d,int[] q){
		
		for(int i=0;i<=n;i++){//��ʼ����һ�м�ֵ
			maxV[i][0]=0;  
		}
		for(int i=0;i<=c;i++){//��ʼ����һ�м�ֵ
			maxV[0][i]=0;  
		}
		/*
		 * �����������ʼ�����Խ����Ż�
		 * ��Ҫ�������ѭ������ѭ��i=0��j=0����� if(i==0||j==0)���Ż�
		 */
		for(int i=1;i<=n;i++){  //����i=0���Ż�
			for(int j=1;j<=c;j++){ //����j=0���Ż�
				maxV[i][j]=0;
				//if(i==0||j==0){maxV[i][j]=0;continue;}
				if(j<w[i])maxV[i][j]=maxV[i-1][j];
				if(j>=w[i]){
					int sum=maxV[i-1][j-w[i]]+v[i];
					if(sum>maxV[i-1][j]){
						maxV[i][j]=sum;
						d[i][j]=w[i];
					}
				}
			}
		}
		
		int s=c;
		q[n]=d[n][c];//���Ž����һ����Ʒ�����������
		//���ݹ������Ž�
		for(int i=n;i>0;i--){
			s=s-q[i];
			q[i-1]=d[i-1][s];
		}
		System.out.println("����ֵΪ��"+maxV[n][c]);
		System.out.println("���Ž������");
		for(int i=1;i<=n;i++){
			System.out.println("ѡ������"+i+"��Ʒ����Ϊ"+q[i]+"");
		}
	}
}
