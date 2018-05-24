package his.CapReader.framer;

import java.io.IOException;

import his.CapReader.packet.http.Headers;
import his.CapReader.packet.http.HttpPacket;
import his.CapReader.packet.http.HttpPacketImpl;
import his.CapReader.packet.http.InputData;
import io.pkts.buffer.Buffer;
import io.pkts.framer.Framer;
import io.pkts.framer.FramingException;
import io.pkts.packet.TransportPacket;
import io.pkts.protocol.Protocol;

public final class HttpFramer implements Framer<TransportPacket, HttpPacket> {

	public static final String REQUEST_IDENTIFIER = " HTTP/1.1";
	public static final String RESPONSE_IDENTIFIER = "HTTP/1.1 ";

	public final static int ZERO = 0;
	private boolean hasRequestData = false;
	private boolean hasResponseData = false;

	@Override
	public Protocol getProtocol() {
		return null;
	}

	@Override
	public boolean accept(Buffer data) throws IOException {
		String bytesAsString = data.toString();
		if (bytesAsString.indexOf("HTTP/1") > -1) {
			return true;
		}
		return false;
	}

	@Override
	public HttpPacket frame(TransportPacket parent, Buffer buffer) throws IOException, FramingException {

		if (parent == null) {
			throw new IllegalArgumentException("The parent frame cannot be null");
		}

		String byteArrayAsString = buffer.toString();
		if (byteArrayAsString.indexOf(REQUEST_IDENTIFIER) > -1) {
			hasRequestData = true;
		}
		if (byteArrayAsString.indexOf(RESPONSE_IDENTIFIER) > -1) {
			hasResponseData = true;
		}

		InputData request;
		InputData response = null;

		if (hasRequestData) {
			request = getRequest(buffer, buffer.getReadableBytes());
			return new HttpPacketImpl(parent, request.getHeaders(), request.getData());
		} else if (hasResponseData) {
			response = getResponse(buffer, buffer.getReadableBytes());
			return new HttpPacketImpl(parent, response.getHeaders(), response.getData());
		}
		return null;
	}

	private InputData getResponse(Buffer buffer, int responseLength, int responseIndex) {
		byte[] response = new byte[responseLength];
		System.arraycopy(buffer, responseIndex, response, ZERO, responseLength);
		String responseAsString = new String(response);
		Headers responseHeaders = getHeaders(responseAsString);
		int responseContentLength = responseHeaders.getContentLength();
		return new InputData(response, responseHeaders);
	}

	private InputData getRequest(Buffer buffer, int responseIndex) {
		byte[] request = new byte[responseIndex];
		System.arraycopy(buffer, ZERO, request, ZERO, responseIndex);
		String requestAsString = new String(request);
		Headers requestHeaders = getHeaders(requestAsString);
		int requestContentLength = requestHeaders.getContentLength();
		return new InputData(request, requestHeaders);
	}

	private static Headers getHeaders(String stringWithHeaders) {
		Headers headers = new Headers();
		String[] tokens = stringWithHeaders.split("\r\n");
		for (String token : tokens) {
			if (token.isEmpty()) {
				break;
			}
			if (token.contains(": ")) {
				String[] values = token.split(": ");
				headers.addHeader(values[0], values[1]);
			}
		}
		return headers;
	}
}
