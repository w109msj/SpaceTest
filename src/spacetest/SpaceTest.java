package spacetest;

import Simple2DEngine.*;

public class SpaceTest implements S2DInterface {
    
    S2DAnimatedGraphic viper;
    S2DGraphic sBG1;
    S2DGraphic sBG2;
    S2DGameLayer backgroundLayer;
    S2DGameLayer foregroundLayer;
    S2DLayer testLayer;
    
    float viperAccel = 0.1f;
    float viperVelocity = 0;
    float viperX = 300;
    
    float sBG1y = 0;
    float sBG2y = 720;
    
    public static void main(String[] args) {
        SpaceTest game = new SpaceTest();
        
        S2DEngine engine = new S2DEngine.Builder(game).fullscreen(false)
                                                      .fps(60)
                                                      .size(1280, 720)
                                                      .title("SPACE TEST")
                                                      .renderMode(RenderMode.VBO_ADVANCED)
                                                      .build();
        engine.runGame();
    }

    @Override
    public void init(S2DEngine engine) {
        engine.batchLoad("files.txt");
        
        backgroundLayer = engine.newS2DGameLayer(1, SortMode.DEPTH_SORTED);
        foregroundLayer = engine.newS2DGameLayer(2, SortMode.DEPTH_SORTED);
        
        sBG1 = engine.newS2DGraphic("SpaceBG.png");
        sBG2 = engine.newS2DGraphic("SpaceBG.png");
        sBG1.setLayer(backgroundLayer);
        sBG2.setLayer(backgroundLayer);
        
        viper = engine.newGraphicFromTemplate("viper");
        viper.setLayer(foregroundLayer);
       
        viper.repeatAnimation("flySmall");
        viper.X(300).Y(250).setScale(2);
    }

    @Override
    public void update(S2DEngine engine) {
        sBG1y--;
        sBG2y--;
        if(sBG1y == -720) sBG1y = 720;
        if(sBG2y == -720) sBG2y = 720;
        sBG1.Y(sBG1y);
        sBG2.Y(sBG2y);
        
        viperVelocity += viperAccel;
        
        if(viperVelocity > 10) viperVelocity = 10;
        if(viperVelocity < -10) viperVelocity = -10;
        
        if(viperX > 590) viperAccel = -0.1f;
        if(viperX < 590) viperAccel = 0.1f;
        
        //viper.rotate(-viperVelocity * 2.5f);
        viperX += viperVelocity;
        //viper.X(viperX);
    }
    
}
