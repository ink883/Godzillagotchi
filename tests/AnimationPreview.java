import com.nova.godzillagotchi.*;import java.io.*;import javax.imageio.*;import javax.imageio.stream.*;import javax.imageio.metadata.*;import java.awt.image.*;
public class AnimationPreview {
 static IIOMetadataNode node(IIOMetadataNode root,String name){for(int i=0;i<root.getLength();i++)if(root.item(i).getNodeName().equals(name))return (IIOMetadataNode)root.item(i);IIOMetadataNode n=new IIOMetadataNode(name);root.appendChild(n);return n;}
 public static void main(String[] args)throws Exception{SkinPreview p=new SkinPreview(args[0]);SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(0);s.city=new City();s.night=true;
 ImageWriter writer=ImageIO.getImageWritersByFormatName("gif").next();try(ImageOutputStream out=ImageIO.createImageOutputStream(new File(args[1]+"-animations.gif"))){writer.setOutput(out);writer.prepareWriteSequence(null);boolean first=true;
 for(int move=0;move<4;move++){s.battle=new Battle(move==3?2:0);s.battle.attack(move,10000);int end=Battle.DURATION_MS[move]+1200;for(int t=0;t<=end;t+=80){long now=10000+t;while(s.battle.waiting&&s.battle.nextAt<=now)s.battle.tick(s.battle.nextAt);s.now=now;s.battleNow=now;
 BufferedImage frame=p.frame(s,480);ImageWriteParam param=writer.getDefaultWriteParam();IIOMetadata meta=writer.getDefaultImageMetadata(ImageTypeSpecifier.createFromRenderedImage(frame),param);String format=meta.getNativeMetadataFormatName();IIOMetadataNode root=(IIOMetadataNode)meta.getAsTree(format);IIOMetadataNode gce=node(root,"GraphicControlExtension");gce.setAttribute("disposalMethod","none");gce.setAttribute("userInputFlag","FALSE");gce.setAttribute("transparentColorFlag","FALSE");gce.setAttribute("delayTime","8");gce.setAttribute("transparentColorIndex","0");if(first){IIOMetadataNode ext=node(root,"ApplicationExtensions"),loop=new IIOMetadataNode("ApplicationExtension");loop.setAttribute("applicationID","NETSCAPE");loop.setAttribute("authenticationCode","2.0");loop.setUserObject(new byte[]{1,0,0});ext.appendChild(loop);first=false;}meta.setFromTree(format,root);writer.writeToSequence(new IIOImage(frame,null,meta),param);
 }
 s.battle=new Battle(2);s.battle.attack(move,10000);s.battleNow=10000+Battle.HIT_MS[move]+80;while(s.battle.waiting&&s.battle.nextAt<=s.battleNow)s.battle.tick(s.battle.nextAt);p.render(s,args[1]+"-"+Battle.MOVES[move].toLowerCase()+".png");
 }
 writer.endWriteSequence();}writer.dispose();System.out.println("Rendered all four full attack sequences and counters, plus four 720x720 impact stills.");
 }
}
