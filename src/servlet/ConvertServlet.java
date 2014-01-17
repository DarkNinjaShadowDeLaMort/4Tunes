package servlet;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import java.io.File;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import manager.chanson.ChansonManager;
import model.Chanson;

@WebServlet(name="ConvertServlet",  urlPatterns = {"/servlet/encode"})
@MultipartConfig
public class ConvertServlet extends HttpServlet{
	private static final long serialVersionUID = -6152463445011550779L;
	
	@EJB
	ChansonManager chansonManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("id");
		
		HttpSession session = request.getSession(false);
		if(session == null || sId == null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		else{
			int id = Integer.valueOf(sId);
			request.setAttribute("id", id);
			request.getRequestDispatcher("/encode.jsp").forward(request, response);
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		else{
			int userId = (int) session.getAttribute("id");
			
			int chansonId = Integer.valueOf(request.getParameter("id"));
			
			Chanson chanson = chansonManager.getChanson(chansonId);
			
			String bitrate = request.getParameter("bitrate");
			String samplingRate = request.getParameter("samplingRate");
			String channel = request.getParameter("channel");
			String format = request.getParameter("format");
						
			String url = chanson.getUrl();
			File source = new File("Musique/"+userId+"/"+url);
			
			url = url.substring(0, url.lastIndexOf('.'));
			url += "."+format;
			
			File target = new File("Musique/"+userId+"/"+url);
			
			
			Integer chan;
			if(channel.charAt(0) == '1'){
				chan = 1;
			}
			else{
				chan = 2;
			}
			
			Integer bitr = 0;
			bitrate = bitrate.substring(0, bitrate.indexOf(' '));
			bitr = Integer.valueOf(bitrate);
			
			encode(source, target, bitr*1000, 44100, chan, format);
			
			double durationInSeconds = 0;
			try{
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(target);
				AudioFormat audioFormat = audioInputStream.getFormat();
				long frames = audioInputStream.getFrameLength();
				durationInSeconds = (frames+0.0) / audioFormat.getFrameRate();  
			}
			catch(Exception e){
				e.printStackTrace();
			}
			chansonManager.addChanson(chanson.getTitre(), (float)durationInSeconds, url, chanson.getArtiste(), chanson.getAlbum(), chanson.getGenre(), userId);
			
			response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
		}
		
		
	}
	
	public void encode(File source, File target, Integer bitrate, Integer samplingRate, Integer channel, String format) {
		System.out.println("bitrate : "+bitrate);
		System.out.println("samplingRate : "+samplingRate);
		System.out.println("channel : "+channel);
		System.out.println("format : "+format);
		
		try {
			String codec = "";
			if(format.equals("mp3")){
				codec = "libmp3lame";
			}
			else if(format.equals("ac3")){
				codec = "ac3";
			}
			else if(format.equals("aac")){
				codec = "libfaac";
			}
			else if(format.equals("wav")){
				codec = "pcm_alaw";
			}
			else if(format.equals("flac")){
				codec = "flac";
			}
			else if(format.equals("ogg")){
				codec = "libvorbis";
			}
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec(codec);
			audio.setBitRate(bitrate);
			audio.setChannels(channel);
			audio.setSamplingRate(samplingRate);
			EncodingAttributes attrs = new EncodingAttributes();
			attrs.setFormat(format);
			attrs.setAudioAttributes(audio);
			Encoder encoder = new Encoder();
			encoder.encode(source, target, attrs);
		} catch (IllegalArgumentException | EncoderException e) {
			e.printStackTrace();
		}
	}
}
