package prv.chenxs.std.nettystd.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyTimentClientHandler extends ChannelHandlerAdapter {
	public final ByteBuf reqMsg;

	public NettyTimentClientHandler() {
		String req = "query current time!";
		byte[] reqBytes = req.getBytes();
		reqMsg = Unpooled.buffer(reqBytes.length);
		reqMsg.writeBytes(reqBytes);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(reqMsg);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf read=(ByteBuf)msg;
		byte[] readBytes=new byte[read.readableBytes()];
		read.readBytes(readBytes);
		String reqMsg=new String(readBytes,"UTF-8");
		System.out.println(reqMsg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}
