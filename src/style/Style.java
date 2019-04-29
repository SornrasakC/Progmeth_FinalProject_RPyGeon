package style;

public class Style
{
	public static String greyButton;
	public static String greyButtonHover;
	static
	{
		greyButton =
				"-fx-background-color: \r\n" + 
				"				        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\r\n" + 
				"				        linear-gradient(#020b02, #3a3a3a),\r\n" + 
				"				        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\r\n" + 
				"				        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\r\n" + 
				"				    -fx-background-insets: 0,1,4,5;\r\n" + 
				"				    -fx-background-radius: 9,8,5,4;\r\n" + 
				"				    -fx-padding: 15 30 15 30;\r\n" + 
				"				    -fx-font-family: \"Helvetica\";\r\n" + 
				"				    -fx-font-size: 18px;\r\n" + 
				"				    -fx-font-weight: bold;\r\n" + 
				"				    -fx-text-fill: #333333;\r\n" + 
				"				    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);";
		
		greyButtonHover = "";
	}
}
