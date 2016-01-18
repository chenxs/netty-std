package prv.chenxs.std.nettystd.tcp.server;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyTcpTimeServerHandler extends ChannelHandlerAdapter{
	private volatile int reqCountter=0;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//获取请求信息
		ByteBuf read=(ByteBuf)msg;
		byte[] readBytes=new byte[read.readableBytes()];
		read.readBytes(readBytes);
		String reqMsg=new String(readBytes,"UTF-8");
		System.out.println("the "+(reqCountter++)+" reqmsg："+reqMsg);
		
		//返回响应信息
		String rspMsg=new Date().toString();
		byte[] writeBytes=rspMsg.getBytes("UTF-8");
		ByteBuf write=Unpooled.copiedBuffer(writeBytes);
		ctx.write(write);
				
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}
	
}
