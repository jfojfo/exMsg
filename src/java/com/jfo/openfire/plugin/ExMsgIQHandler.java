package com.jfo.openfire.plugin;

import org.dom4j.Element;
import org.jivesoftware.openfire.IQHandlerInfo;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.group.GroupManager;
import org.jivesoftware.openfire.handler.IQHandler;
import org.jivesoftware.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;

public class ExMsgIQHandler extends IQHandler {
	private static final Logger Log = LoggerFactory.getLogger(ExMsgIQHandler.class);
	private static final String MODULE_NAME = "exmsg handler";
    private static final String NAME_SPACE = "jfo:iq:exmsg";

    private IQHandlerInfo info;
    
	public ExMsgIQHandler() {
		super(MODULE_NAME);
		info = new IQHandlerInfo("exmsg", NAME_SPACE);
	}

	public IQHandlerInfo getInfo() {
		return info;
	}

	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		Log.info("handle iq:" + packet.toXML());
		IQ reply = IQ.createResultIQ(packet);
		Element childElement = packet.getChildElement();
		if (!IQ.Type.get.equals(packet.getType())) {
			Log.info("Illegal request type");
			reply.setChildElement(childElement.createCopy());
			reply.setError(PacketError.Condition.bad_request);
			return reply;
		}
		String userName = packet.getFrom().getNode();
        Element elem = reply.setChildElement("query", childElement.getNamespaceURI());
        elem.addElement("echo").addAttribute("id", userName).addText("Hello " + userName);
        Log.info(reply.toXML());

		return reply;
	}

}
