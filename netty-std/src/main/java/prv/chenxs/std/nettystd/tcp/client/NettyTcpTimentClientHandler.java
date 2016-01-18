package prv.chenxs.std.nettystd.tcp.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyTcpTimentClientHandler extends ChannelHandlerAdapter {
	public final byte[]  reqBytes;
	public volatile int rspCounnter=0;
	public NettyTcpTimentClientHandler() {
		String req = "query current time!";
		reqBytes = req.getBytes();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf bf=null;
		for(int i=0;i<100;i++){
			bf=Unpooled.buffer(reqBytes.length);
			bf.writeBytes(reqBytes);
			ctx.writeAndFlush(bf);
		}
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf read=(ByteBuf)msg;
		byte[] readBytes=new byte[read.readableBytes()];
		read.readBytes(readBytes);
		String rspMsg=new String(readBytes,"UTF-8");
		System.out.println("the "+(rspCounnter++)+" rspMsg£º"+rspMsg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}
