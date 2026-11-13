import com.nova.godzillagotchi.*;import java.io.*;import javax.imageio.*;import javax.imageio.stream.*;import javax.imageio.metadata.*;import java.awt.image.*;
public class FeedPreview {
 static IIOMetadataNode node(IIOMetadataNode root,String name){for(int i=0;i<root.getLength();i++)if(root.item(i).getNodeName().equals(name))return (IIOMetadataNode)root.item(i);IIOMetadataNode n=new IIOMetadataNode(name);root.appendChild(n);return n;}
 public static void main(String[] args)throws Exception{SkinPreview p=new SkinPreview(args[0]);SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(0);s.city=new City();s.night=true;
 ImageWriter writer=ImageIO.getImageWritersByFormatName("gif").next();try(ImageOutputStream out=ImageIO.createImageOutputStream(new File(args[1]+"-animations.gif"))){writer.setOutput(out);writer.prepareWriteSequence(null);boolean first=true;
 for(int fish=0;fish<3;fish++){s.fishType=fish;
 s.effect="fish";s.effectEnd=10000+SoundCues.FEED_MS;s.message=new String[]{"Salmon","Tuna","Sardines"}[fish]+"! CHOMP. Happy kaiju.";s.pet.feed();for(int t=0;t<=SoundCues.FEED_MS+200;t+=80){s.now=10000+t;
 BufferedImage frame=p.frame(s,480);ImageWriteParam param=writer.getDefaultWriteParam();IIOMetadata meta=writer.getDefaultImageMetadata(ImageTypeSpecifier.createFromRenderedImage(frame),param);String format=meta.getNativeMetadataFormatName();IIOMetadataNode root=(IIOMetadataNode)meta.getAsTree(format);IIOMetadataNode gce=node(root,"GraphicControlExtension");gce.setAttribute("disposalMethod","none");gce.setAttribute("userInputFlag","FALSE");gce.setAttribute("transparentColorFlag","FALSE");gce.setAttribute("delayTime","8");gce.setAttribute("transparentColorIndex","0");if(first){IIOMetadataNode ext=node(root,"ApplicationExtensions"),loop=new IIOMetadataNode("ApplicationExtension");loop.setAttribute("applicationID","NETSCAPE");loop.setAttribute("authenticationCode","2.0");loop.setUserObject(new byte[]{1,0,0});ext.appendChild(loop);first=false;}meta.setFromTree(format,root);writer.writeToSequence(new IIOImage(frame,null,meta),param);
 }
 s.now=10500;p.render(s,args[1]+"-fish-"+fish+"-feeding.png");s.now=11500;p.render(s,args[1]+"-fish-"+fish+"-bite.png");s.now=11900;p.render(s,args[1]+"-fish-"+fish+"-eating.png");
 }
 writer.endWriteSequence();}writer.dispose();System.out.println("Rendered feeding animation and three 720-square feeding stages.");
 }
}
