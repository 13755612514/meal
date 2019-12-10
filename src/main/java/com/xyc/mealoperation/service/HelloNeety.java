package com.xyc.mealoperation.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloNeety {
    public static void main(String[] args) {
        int port = 8080;
        new HelloNeety().init(port);
    }
    public void init(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());

            ChannelFuture f =  server.bind(port).sync();
            System.out.println(Thread.currentThread().getName() + ",服务器开始监听。");
            f.channel().closeFuture().sync();
            System.out.println("---------------------f.channel().closeFuture().sync()");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("---------------------bossGroup.shutdownGracefully()");
            bossGroup.shutdownGracefully();
            System.out.println("---------------------workerGroup.shutdownGracefully()");
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            arg0.pipeline().addLast(new TimeServerHandler());
        }
    }
}
