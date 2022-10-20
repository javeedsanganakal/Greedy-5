//Approach - 1: DP
//Time Complexity : O(m*n)
//Space Complexity : O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals('*')) return true;
        int sLen = s.length();
        int pLen = p.length();
        
        
        boolean [][] dp = new boolean [pLen+1][sLen+1];
        
        // - matches -
        dp[0][0] = true;
        
        
        for(int i=1; i<=pLen; i++){
            //if pattern at end "*" then its true;  or else default its false
            // * matches -, take the above case
            if(p.charAt(i-1) == '*'){
                 dp[i][0] = dp[i-1][0];
            }
        }
        
        for(int i=1; i<=pLen; i++){
            for(int j=1; j<=sLen; j++){
                
                if(p.charAt(i-1) !='*'){
                    if(s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?'){
                        //top left diagonal
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{
                    //case 0 or case 1
                    //above and one step back
                    dp[i][j] = dp[i-1][j] ||  dp[i][j-1];
                }  
            } 
        }
        return dp[pLen][sLen];
    }
}




//Approach - 2: 4 Pointers
//Time Complexity : O(SlogP)
//Space Complexity : O(1

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals('*')) return true;
        int sLen = s.length();
        int pLen = p.length();
        
        int sp = 0;
        int pp = 0;
        
        int sStar = -1;
        int pStar = -1;
        
        while(sp < sLen){
            //1st Case
            if(pp < pLen && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }
            //2nd Case
            else if(pp < pLen && p.charAt(pp) == '*') { 
                sStar = sp;
                pStar = pp;
                pp++;
            }
            //3rd Case
            else if(pStar == -1){
                return false;
            }
            //4th Case
            else{
                sp = sStar + 1;
                sStar = sp;
                pp = pStar + 1;
            }
        }
        
        while(pp < pLen){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }
        return true;
    }
}
