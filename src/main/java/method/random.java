package method;


/*
* 此方法用于生成验证码
* */
public class random {
    public String getRandomNumber(){
        return String.valueOf((int)(Math.random()*1000000));
    }
}
