package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public static Image tent;
	public static Image genericVendor;
	public static Image emiliaN1;
	public static Image emiliaN2;
	public static Image emiliaE1;
	public static Image emiliaE2;
	public static Image emiliaW1;
	public static Image emiliaW2;
	public static Image emiliaS1;
	public static Image emiliaS2;
	public static Image emiliaAttack;
	public static Image emiliaSpell;
	public static Image emiliaFullBody;
	
	//items
	public static Image redCookie;
	public static Image blueCookie;
	public static Image hpPotion;
	public static Image mpPotion;
	public static Image chickenDinner;
	public static Image cocain;
	public static Image phoenixKit;
	public static Image lowRecPotion;
	public static Image highRecPotion;
	public static Image m44;
	public static Image rightArm;
	public static Image otenTear;
	public static Image mirrorForce;
	public static Image coin;
	
	//weapons
	public static Image woodenStick;
	public static Image excalibur;
	public static Image yggdrasilBranch;
	public static Image rustedIronSword;
	public static Image rottenWoodStaff;
	public static Image slightlyDullSteelSword;
	public static Image shinyStoneRod;
	public static Image polishedSteelSword;
	public static Image rubyStaff;
	public static Image fieryBlade;
	public static Image mythrilSword;
	public static Image bookOfPower;
	public static Image tellulam;
	public static Image grimoirOfTruth;
	public static Image woodenSword;
	public static Image slightlyMagicalStick;
	public static Image ironSword;
	public static Image woodenStaff;
	public static Image steelSword;
	public static Image emeraldStaff;
	public static Image palladiumSword;
	public static Image casterRing;
	public static Image chineseAdamantiumSword;
	public static Image chineseDiamondStaff;
	
	//armour
	public static Image startingShirt;
	public static Image startingPants;
	public static Image startingBoots;
	public static Image leatherShirt;
	public static Image leatherPants;
	public static Image leatherBoots;
	public static Image chainShirt;
	public static Image chainPants;
	public static Image chainBoots;
	public static Image steelShirt;
	public static Image steelPants;
	public static Image steelBoots;
	public static Image magicMetalShirt;
	public static Image magicMetalPants;
	public static Image magicMetalBoots;
	public static Image adamantiumShirt;
	public static Image adamantiumPants;
	public static Image adamantiumBoots;
	
	
	//TODO add some sound
	public static AudioClip song;
	
	//Backgrounds
	public static Image epilogueBackground;
	public static Image mainMenuBackground;
	public static Image itemShopBackground;
	public static Image blacksmithShopBackground;
	public static Image villageBackground;
	
	public static Image placeHolder;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		tent = new Image(ClassLoader.getSystemResourceAsStream("tentSprite.png"));
		genericVendor = new Image(ClassLoader.getSystemResourceAsStream("generic-rpg-vendor.png"));
		emiliaE1 = new Image(ClassLoader.getSystemResourceAsStream("emiliaright01.PNG"));
		emiliaE2 = new Image(ClassLoader.getSystemResourceAsStream("emiliaright02.PNG"));
		emiliaW1 = new Image(ClassLoader.getSystemResourceAsStream("emilialeft01.PNG"));
		emiliaW2 = new Image(ClassLoader.getSystemResourceAsStream("emilialeft02.PNG"));
		emiliaN1 = new Image(ClassLoader.getSystemResourceAsStream("emiliaback01.png"));
		emiliaN2 = new Image(ClassLoader.getSystemResourceAsStream("emiliaback02.png"));
		emiliaS1 = new Image(ClassLoader.getSystemResourceAsStream("emiliafront01.png"));
		emiliaS2 = new Image(ClassLoader.getSystemResourceAsStream("emiliafront02.png"));
		emiliaAttack = new Image(ClassLoader.getSystemResourceAsStream("emiliaAttack.png"));
		emiliaFullBody = new Image(ClassLoader.getSystemResourceAsStream("bigEmilia.png"),650,650,true,false);
		
		
		//items
		redCookie = new Image(ClassLoader.getSystemResourceAsStream("redcookie.png"),80,80,true,false);
		blueCookie = new Image(ClassLoader.getSystemResourceAsStream("bluecookie.png"),80,80,true,false);
		hpPotion = new Image(ClassLoader.getSystemResourceAsStream("redpotion.png"),80,80,true,false);
		mpPotion = new Image(ClassLoader.getSystemResourceAsStream("bluepotion.png"),80,80,true,false);
		chickenDinner = new Image(ClassLoader.getSystemResourceAsStream("chickendinner.png"),80,80,true,false);
		cocain = new Image(ClassLoader.getSystemResourceAsStream("cocain.png"),80,80,true,false);
		phoenixKit = new Image(ClassLoader.getSystemResourceAsStream("phoenixkit2.png"),80,80,true,false);
		lowRecPotion = new Image(ClassLoader.getSystemResourceAsStream("lowrecoverypotion.png"),80,80,true,false);
		highRecPotion = new Image(ClassLoader.getSystemResourceAsStream("highrecoverypotion.png"),80,80,true,false);
		m44 = new Image(ClassLoader.getSystemResourceAsStream("M44a.png"),120,80,true,false);
		rightArm = new Image(ClassLoader.getSystemResourceAsStream("shroudRightArm.png"),80,80,true,false);
		otenTear = new Image(ClassLoader.getSystemResourceAsStream("otentear.png"),80,80,true,false);
		mirrorForce = new Image(ClassLoader.getSystemResourceAsStream("trapcard.png"),80,80,true,false);
		coin = new Image(ClassLoader.getSystemResourceAsStream("coin.png"),80,80,true,false);
		
		//weapons
		woodenStick  = new Image(ClassLoader.getSystemResourceAsStream("woodenStick.png"));
		excalibur = new Image(ClassLoader.getSystemResourceAsStream("excalibur.png"));
		yggdrasilBranch = new Image(ClassLoader.getSystemResourceAsStream("yggdrasilBranch.png"));
		rustedIronSword = new Image(ClassLoader.getSystemResourceAsStream("rustyIronSword.png"));
		rottenWoodStaff = new Image(ClassLoader.getSystemResourceAsStream("rottenWoodenStaff.png"));
		slightlyDullSteelSword = new Image(ClassLoader.getSystemResourceAsStream("dullSteelSword.png"));
		shinyStoneRod = new Image(ClassLoader.getSystemResourceAsStream("shinyStoneRod.png"));
		polishedSteelSword = new Image(ClassLoader.getSystemResourceAsStream("polishedSteelSword.png"));
		rubyStaff = new Image(ClassLoader.getSystemResourceAsStream("rubyStaff.png"));
		fieryBlade = new Image(ClassLoader.getSystemResourceAsStream("fieryBlade.png"));
		mythrilSword = new Image(ClassLoader.getSystemResourceAsStream("mithrilSword.png"));
		bookOfPower = new Image(ClassLoader.getSystemResourceAsStream("bookOfPower.png"));
		tellulam = new Image(ClassLoader.getSystemResourceAsStream("tellulum.png"));
		grimoirOfTruth = new Image(ClassLoader.getSystemResourceAsStream("grimoirOfTruth.png"));
		woodenSword = new Image(ClassLoader.getSystemResourceAsStream("woodenSword.png"));
		slightlyMagicalStick = new Image(ClassLoader.getSystemResourceAsStream("slightlyMagicalStick.png"));
		ironSword = new Image(ClassLoader.getSystemResourceAsStream("ironSword.png"));
		woodenStaff = new Image(ClassLoader.getSystemResourceAsStream("woodenStaff.png"));
		steelSword = new Image(ClassLoader.getSystemResourceAsStream("steelSword.png"));
		emeraldStaff = new Image(ClassLoader.getSystemResourceAsStream("emeraldStaff.png"));
		palladiumSword = new Image(ClassLoader.getSystemResourceAsStream("palladiumSword.png"));
		casterRing = new Image(ClassLoader.getSystemResourceAsStream("casterRing.png"));
		chineseAdamantiumSword = new Image(ClassLoader.getSystemResourceAsStream("chineseExcalibur.png"));
		chineseDiamondStaff = new Image(ClassLoader.getSystemResourceAsStream("chineseDiamondStaff.png"));
		
		//armours
		startingShirt = new Image(ClassLoader.getSystemResourceAsStream("startingShirt.png"));
		startingPants = new Image(ClassLoader.getSystemResourceAsStream("startingPants.png"));
		startingBoots = new Image(ClassLoader.getSystemResourceAsStream("startingBoots.png"));
		leatherShirt = new Image(ClassLoader.getSystemResourceAsStream("leatherShirt.png"));
		leatherPants = new Image(ClassLoader.getSystemResourceAsStream("leatherPants.png"));
		leatherBoots = new Image(ClassLoader.getSystemResourceAsStream("leatherBoots.png"));
		chainShirt = new Image(ClassLoader.getSystemResourceAsStream("chainShirt.png"));
		chainPants = new Image(ClassLoader.getSystemResourceAsStream("chainPants.png"));
		chainBoots = new Image(ClassLoader.getSystemResourceAsStream("chainBoots.png"));
		steelShirt = new Image(ClassLoader.getSystemResourceAsStream("steelShirt.png"));
		steelPants = new Image(ClassLoader.getSystemResourceAsStream("steelPants.png"));
		steelBoots = new Image(ClassLoader.getSystemResourceAsStream("steelBoots.png"));
		magicMetalShirt = new Image(ClassLoader.getSystemResourceAsStream("magicShirt.png"));
		magicMetalPants = new Image(ClassLoader.getSystemResourceAsStream("magicPants.png"));
		magicMetalBoots = new Image(ClassLoader.getSystemResourceAsStream("magicBoots.png"));
		adamantiumShirt = new Image(ClassLoader.getSystemResourceAsStream("adamantiumShirt.png"));
		adamantiumPants = new Image(ClassLoader.getSystemResourceAsStream("adamantiumPants.png"));
		adamantiumBoots = new Image(ClassLoader.getSystemResourceAsStream("adamantiumBoots.png"));
		
		
		//backgrounds
		epilogueBackground = new Image(ClassLoader.getSystemResourceAsStream("epilogue.png"));
		mainMenuBackground = new Image(ClassLoader.getSystemResourceAsStream("MainMenu.jpg"));
		itemShopBackground = new Image(ClassLoader.getSystemResourceAsStream("itemShop.PNG"));
		blacksmithShopBackground = new Image(ClassLoader.getSystemResourceAsStream("blacksmithBG.jpg"));
		villageBackground = new Image(ClassLoader.getSystemResourceAsStream("villageBG.jpg"));
		
		placeHolder = new Image(ClassLoader.getSystemResourceAsStream("WIP.png"),80,80,true,false);
	}

	public void add(IRenderable entity) {
		System.out.println("added entity");
		entities.add(entity);
		Collections.sort(entities, comparator);
//		for(IRenderable x: entities){
//			
//		}
	}



	public List<IRenderable> getEntities() {
		return entities;
	}
}
