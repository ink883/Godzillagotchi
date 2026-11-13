import com.nova.godzillagotchi.*;import java.io.*;import javax.imageio.*;import javax.imageio.stream.*;import javax.imageio.metadata.*;import java.awt.image.*;
public class EnemyPreview {
 static Battle counter(int who,boolean special){Battle b=new Battle(who);b.turn=special?3:1;b.lastMove=0;b.enemyEvent=true;b.phase=3;b.waiting=true;b.eventAt=10000;b.nextAt=10000+b.enemyHitMs();b.lastEnemyDamage=Battle.POWER[who]+(special?5:0);b.log=Battle.SPECIALS[who]+" / wind-up";return b;}
 static IIOMetadataNode node(IIOMetadataNode root,String name){for(int i=0;i<root.getLength();i++)if(root.item(i).getNodeName().equals(name))return (IIOMetadataNode)root.item(i);IIOMetadataNode n=new IIOMetadataNode(name);root.appendChild(n);return n;}
 public static void main(String[] args)throws Exception{SkinPreview p=new SkinPreview(args[0]);SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(0);s.city=new City();s.night=true;
 ImageWriter writer=ImageIO.getImageWritersByFormatName("gif").next();try(ImageOutputStream out=ImageIO.createImageOutputStream(new File(args[1]+"-animations.gif"))){writer.setOutput(out);writer.prepareWriteSequence(null);boolean first=true;
 for(int move=0;move<6;move++){s.battle=counter(move,true);int end=s.battle.enemyDurationMs()+400;for(int t=0;t<=end;t+=80){long now=10000+t;while(s.battle.waiting&&s.battle.nextAt<=now)s.battle.tick(s.battle.nextAt);s.now=now;s.battleNow=now;
 BufferedImage frame=p.frame(s,480);ImageWriteParam param=writer.getDefaultWriteParam();IIOMetadata meta=writer.getDefaultImageMetadata(ImageTypeSpecifier.createFromRenderedImage(frame),param);String format=meta.getNativeMetadataFormatName();IIOMetadataNode root=(IIOMetadataNode)meta.getAsTree(format);IIOMetadataNode gce=node(root,"GraphicControlExtension");gce.setAttribute("disposalMethod","none");gce.setAttribute("userInputFlag","FALSE");gce.setAttribute("transparentColorFlag","FALSE");gce.setAttribute("delayTime","8");gce.setAttribute("transparentColorIndex","0");if(first){IIOMetadataNode ext=node(root,"ApplicationExtensions"),loop=new IIOMetadataNode("ApplicationExtension");loop.setAttribute("applicationID","NETSCAPE");loop.setAttribute("authenticationCode","2.0");loop.setUserObject(new byte[]{1,0,0});ext.appendChild(loop);first=false;}meta.setFromTree(format,root);writer.writeToSequence(new IIOImage(frame,null,meta),param);
 }
 for(int special=0;special<2;special++){s.battle=counter(move,special==1);s.battleNow=10000+s.battle.enemyHitMs()+70;while(s.battle.waiting&&s.battle.nextAt<=s.battleNow)s.battle.tick(s.battle.nextAt);p.render(s,args[1]+"-enemy-"+move+(special==1?"-special":"-basic")+".png");}

 }
 writer.endWriteSequence();}writer.dispose();System.out.println("Rendered all six signature animations and twelve 720-square basic/special contact stills.");
 }
}
