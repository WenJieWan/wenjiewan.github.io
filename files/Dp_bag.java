package data;

public class Dp_bag {

	public static void main(String[] args) {
		int n=5;//物品数量 
		int c=6;//背包容量
		int[] w=new int[]{0,3,2,1,4,5};//赋值初始化重量
		int[] v=new int[]{0,25,20,15,40,50};//赋值初始化价值
		int[][] maxV=new int[n+1][c+1];//表格
		int[][] d=new int[n+1][c+1];//记录分配重量
		int[] q=new int[n+1];//构造最优解
		//创建对象进行计算
		Dp_bag dp_bag=new Dp_bag();
		dp_bag.bag(n,c,w,v,maxV,d,q);
	}
	
	public void bag(int n,int c,int[] w,int[] v,int[][] maxV,int[][] d,int[] q){
		
		for(int i=0;i<=n;i++){//初始化第一列价值
			maxV[i][0]=0;  
		}
		for(int i=0;i<=c;i++){//初始化第一行价值
			maxV[0][i]=0;  
		}
		/*
		 * 上面的两个初始化可以进行优化
		 * 需要对下面的循环设置循环i=0与j=0，配合 if(i==0||j==0)来优化
		 */
		for(int i=1;i<=n;i++){  //设置i=0，优化
			for(int j=1;j<=c;j++){ //设置j=0，优化
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
		q[n]=d[n][c];//最优解最后一个物品被分配的重量
		//回溯构造最优解
		for(int i=n;i>0;i--){
			s=s-q[i];
			q[i-1]=d[i-1][s];
		}
		System.out.println("最大价值为："+maxV[n][c]);
		System.out.println("最优解情况：");
		for(int i=1;i<=n;i++){
			System.out.println("选择分配第"+i+"物品重量为"+q[i]+"");
		}
	}
}
