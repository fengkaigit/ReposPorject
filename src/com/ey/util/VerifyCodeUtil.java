package com.ey.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeUtil {
	// 验证码图片的宽度。
	private int width = 100;
	// 验证码图片的高度。
	private int height = 40;
	// 验证码字符个数
	private int codeCount = 4;
	private int x = 0;
	// 字体高度
	private int fontHeight;
	private int codeY;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public VerifyCodeUtil() {
		x = width / (codeCount + 1);
		fontHeight = height - 5;
		codeY = height - 4;
	}
	public BufferedImage generateCode(StringBuffer randomCode){
		  BufferedImage buffImg = new BufferedImage(width, height,         
	                BufferedImage.TYPE_INT_RGB);         
	        Graphics2D g = buffImg.createGraphics();         
	        // 创建一个随机数生成器类         
	        Random random = new Random();         
	        // 将图像填充为白色         
	        g.setColor(new Color(230,230,204));         
	        g.fillRect(0, 0, width, height);         
	        // 创建字体，字体的大小应该根据图片的高度来定。         
	        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);         
	        // 设置字体。         
	        g.setFont(font);         
	        // 画边框。         
	        //g.setColor(Color.BLACK);         
	        g.drawRect(0, 0, width - 1, height - 1);         
	        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。         
	       // g.setColor(Color.BLACK);
	        int red = 13, green = 7, blue = 221;  
	        g.setColor(new Color(red, green, blue));
	        for (int i = 0; i < 10; i++) {         
	            int x = random.nextInt(width);         
	            int y = random.nextInt(height);         
	            int xl = random.nextInt(12);         
	            int yl = random.nextInt(12);         
	            g.drawLine(x, y, x + xl, y + yl);         
	        }       
	              
	        // 随机产生codeCount数字的验证码。    
	        
	        for (int i = 0; i < codeCount; i++) { 
	        	g.rotate(0) ; 
	            // 得到随机产生的验证码数字。         
	            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);         
	            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。         
	           /* red = random.nextInt(255);         
	            green = random.nextInt(255);         
	            blue = random.nextInt(255);     */    
	            // 用随机产生的颜色将验证码绘制到图像中。 
	           // if(i<2){
	            	 //g.rotate(0.45) ; 
	           // }else{
	            	//g.rotate(-0.45,i * x+10,codeY) ; 
	           // }
	                  
	            g.drawString(strRand, i * x+10, codeY);         
	            // 将产生的四个随机数组合在一起。         
	            randomCode.append(strRand);         
	        }
	        return buffImg;
	    }
}
