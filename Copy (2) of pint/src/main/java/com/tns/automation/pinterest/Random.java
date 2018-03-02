package com.tns.automation.pinterest;

import java.util.*;

import org.openqa.selenium.chrome.ChromeOptions;

public class Random {
	public static String randomAgent() {
		String[] strUserAgent= {"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36"
				,"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2226.0 Safari/537.36","Mozilla/5.0 (Windows NT 6.4; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2224.3 Safari/537.36","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1","Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)","Googlebot/2.1 (+http://www.googlebot.com/bot.html)","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:2.0) Treco/20110515 Fireweb Navigator/2.4"
				,"Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; SV1; Crazy Browser 9.0.04)","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0; Crazy Browser 3.1.0)"};
java.util.Random ran=new java.util.Random();
int any=ran.nextInt(strUserAgent.length);

return strUserAgent[any];
	}
	static String formatTime(long millis) {
		  long seconds = millis / 1000;
		  if (seconds > 60) {
		    long minutes = seconds / 60;
		    seconds -= (minutes * 60);
		    if (minutes > 1) {
		      return String.format("%d minutes, %s",
		          minutes, formatSeconds(seconds));
		    } else {
		      return String.format("1 minute, %s",
		          formatSeconds(seconds));
		    }
		  }
		  return formatSeconds(seconds);
		}
	private static String formatSeconds(long seconds) {
		  return (seconds != 1) ? String.format(
		      "%d seconds", seconds) : "1 second";
		}

	public static void main(String[] args) {
		System.out.println("Random: "+randomAgent());
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-agent="+randomAgent());
        long startTime = System.currentTimeMillis();
        long endTime = (startTime * 60 * 1000);
        while (System.currentTimeMillis() < endTime) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
          }
          System.out.println(formatTime(endTime
              - System.currentTimeMillis()));
        }
        System.out.println("Time's Up!");
      }

	}


