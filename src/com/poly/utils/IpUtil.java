package com.poly.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;


public class IpUtil {

	/**
	 * 
	 * @param request
	 *            IP
	 * 
	 * @return IP Address
	 */

	public static String getIpAddrByRequest(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("Proxy-Client-IP");

		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("WL-Proxy-Client-IP");

		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getRemoteAddr();

		}

		return ip;

	}

	/**
	 * 
	 * 
	 * @throws SocketException
	 */

	public static String getRealIp() throws SocketException {

		String localip = null;

		String netip = null;

		Enumeration<NetworkInterface> netInterfaces =

		NetworkInterface.getNetworkInterfaces();

		InetAddress ip = null;

		boolean finded = false;

		while (netInterfaces.hasMoreElements() && !finded) {

			NetworkInterface ni = netInterfaces.nextElement();

			Enumeration<InetAddress> address = ni.getInetAddresses();

			while (address.hasMoreElements()) {

				ip = address.nextElement();

				if (!ip.isSiteLocalAddress()

				&& !ip.isLoopbackAddress()

				&& ip.getHostAddress().indexOf(":") == -1) {

					netip = ip.getHostAddress();

					finded = true;

					break;

				} else if (ip.isSiteLocalAddress()

				&& !ip.isLoopbackAddress()

				&& ip.getHostAddress().indexOf(":") == -1) {

					localip = ip.getHostAddress();

				}

			}

		}

		if (netip != null && !"".equals(netip)) {

			return netip;

		} else {

			return localip;

		}

	}

}
