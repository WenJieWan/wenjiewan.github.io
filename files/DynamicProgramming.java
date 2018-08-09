package data;

public class DynamicProgramming {

	public static void main(String[] args) {
		//����������������Դ������Ӧ�������ϵ��
		int m,n;//m�����ܶ�ȣ�n�����ܹ�����
		n=3;//n=1,2,3...��������1��2��3
		m=8;//m=0,1,2,3...����������0��1��2��3
		//�������鶼�Ǵ�0��ʼ��Ϊ�˷���۲죬���Ե�һ�����ǲ��ã���Ҫn+1����ռ䣬���Ϊ0��Ҫ��������m+1
		int[][] g=new int[n+1][m+1];//g[i][j]������i�����̱�����j��Դ��Ӧ�������
		int[][] f=new int[n+1][m+1];//f[i][j]�������ֵ����ǰi��̷�����j��Դ���������ֵ
		int[][] d=new int[n+1][m+1];//d[i][j]�������f[i][j]�Ѿ����������¶�Ӧ�ĵ�i�����Դ�����ȡ�
		int[] q=new int[n+1];//q[i]������i�����̱��������Դ��ȣ�Ҳ����˵q���鱣�����Ž��Ӧ��ÿ��̵ķ�����
		//��ʼ�����̶�Ӧ��Դ����������
		//�����Ҿ�͵�����������ݳ�ʼ���õĴ�С����һ����¼���ˣ�ֱ�Ӿ����¸�ֵ��ʼ��
		g=new int[][]{{0,0,0,0,0,0,0,0,0},
			{0,5,15,40,80,90,95,98,100},
			{0,5,15,40,60,70,73,74,75},
			{0,4,26,40,50,51,52,53,54}};
		
		DynamicProgramming dp = new DynamicProgramming();
		//����Ҫ��ķ������µ����Ž����
		int num=7;
		//��ʼ��̬�滮����
		dp.invest(num, n, g, f, d, q);
	}
	
	public  void  invest(int m,int n,int[][] g,int[][] f,int[][] d,int[] q){
		//���ȱ����ȶԵ�һ�����̵����������ֵ���г�ʼ��
		for(int j=0;j<=m;j++){
			//�������ʼ�������еڶ���������ʱ����������һ��̶�Ϊ0��û�������Ž���
			f[1][j]=g[1][j];
			//һ������d�����¼��һ��̷����Ƚ��б���
			d[1][j]=j;
		}
		//��ʼ�ӵڶ���̽��м������Ž�
		for(int i=2;i<=n;i++){
			//�Ե����ϱ������з������
			for(int j=0;j<=m;j++){
				f[i][j]=0;//��ʼ����i�����j��Դ���������ֵ
				//����ǰһ��̵ķ���K���������ֵ
				for(int k=0;k<=j;k++){
					int sum=f[i-1][j-k]+g[i][k];//����ǰһ���(j-k)��Դ���������+����K��Դ����ֵ
					if(sum>f[i][j]){//����������
						f[i][j]=sum;//��ֵ�������ֵ
						d[i][j]=k;//��¼����ֵ
					}
				}
			}
		}
		/*������ÿ�ε����㹤�̵�ʱ��ǰi��̵����Ž��ѱ�������ظ����ѭ������֪��i�ﵽn��
		��ô�������Ž������ˣ�ֻ����������Ž��������Ϊ����ȡ������ֻ�Ǳ�����d��������Ҫ���������Ѱ����ɸѡ������
		*/
		//��ʼ�������Ž�
		int s=m;//��ʼ��sΪm������
		q[n]=d[n][m];//q[n]������㵽���һ�����̵����ŷ������Ӧ����ӹ���ɵ�
		for(int i=n-1;i>0;i--){ //��ʼ��i=n-1,�ӵ����ڶ�����ǰ�����������Ž�
			s = s-q[i+1];	//ǰһ�����Ž�ķ����ܶ��
			q[i] = d[i][s]; //��¼��ǰ���̱��������Դ��
		}
		//�������Ž��ѹ�����ɣ����q���鼴��
		System.out.println("�������ֵΪ��"+f[n][m]);
		System.out.println("���Ž������");
		for(int i=1;i<=n;i++){
			System.out.println("��"+i+"���̷�����Դ��Ϊ��"+q[i]);
		}
	}
}