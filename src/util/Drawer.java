package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Drawer {

	public static void drawCenterLines(Graphics2D g, Color c, int width, int height) {
		g.setColor(c);
		g.drawLine(width / 2, 0, width / 2, height);
		g.drawLine(0, height / 2, width, height / 2);
	}
	
	public static void fillComponent(Graphics2D g, Color c, int width, int height) {
		g.setColor(c);
		g.fillRect(0, 0, width, height);
	}
	
	public static void drawRect(Graphics2D g, Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
	
	public static void drawRect(Graphics2D g, Color c, Rectangle r) {
		g.setColor(c);
		g.fillRect(r.x, r.y, r.width, r.height);
	}
	
	public static void drawImage(Graphics2D g, BufferedImage b, int x, int y, int width, int height) {
		g.drawImage(b, x, y, width, height, null);
	}
	
	public static void drawGrid(Graphics2D g, Color c, int x, int y, int width, int height, int tileWidth, int tileHeight) {
		g.setColor(c);
		
		for (int row = 0; row < height / tileHeight; row++)
			g.drawLine(0 - x, tileHeight * row - y, width - x, tileHeight * row - y);
		
		for (int col = 0; col < width / tileWidth; col++)
			g.drawLine(col * tileWidth - x, 0 - y, col * tileWidth - x, height - y);
	}
	
	public static void drawStringCenterH(Graphics2D g, String s, Font f, Color c, int width, int y) {
		g.setColor(c);
		g.setFont(f);
		int fWidth = getStringWidth(g, s, f);
		int x = (int)(width / 2 - fWidth / 2);
		g.drawString(s, x, y);
	}
	
	public static void drawStringCenterV(Graphics2D g, String s, Font f, Color c, int height, int x) {
		g.setColor(c);
		g.setFont(f);
		int fHeight = getStringHeight(g, s, f);
		int y = (int)(height / 2 + fHeight / 2);
		g.drawString(s, x, y);
	}
	
	public static void drawCenteredStrings(Graphics2D g, String[] strings, Font[] fonts, Color[] colors, int width, int height, int separator, int dx, int dy) {
		int totalHeight = 0;
		int moyHeight = 0;
		
		for (int i = 0; i < strings.length; i++) {
			totalHeight += getStringHeight(g, strings[i], fonts[i]);
		}
		
		moyHeight = totalHeight / strings.length;
		totalHeight += separator * (strings.length - 1);
		
		for (int i = 0; i < strings.length; i++) {
			g.setColor(colors[i]);
			g.setFont(fonts[i]);
			
			int x = width / 2 - getStringWidth(g, strings[i], fonts[i]) / 2;
			int y = height / 2 - totalHeight / 2 + moyHeight + i * moyHeight + i * separator;
			
			g.drawString(strings[i], x + dx, y + dy);
		}
	}
	
	private static int getStringHeight(Graphics2D g, String s, Font font) {
		GlyphVector gv = font.layoutGlyphVector(g.getFontRenderContext(), s.toCharArray(), 0, s.length(), Font.LAYOUT_LEFT_TO_RIGHT);
		Rectangle2D rec = gv.getVisualBounds();
		return (int) rec.getHeight();
	}
	
	private static int getStringWidth(Graphics2D g, String s, Font font) {
		GlyphVector gv = font.layoutGlyphVector(g.getFontRenderContext(), s.toCharArray(), 0, s.length(), Font.LAYOUT_LEFT_TO_RIGHT);
		Rectangle2D rec = gv.getVisualBounds();
		return (int) rec.getWidth();
	}
	
	public static void drawCircle() {
		
	}
}
