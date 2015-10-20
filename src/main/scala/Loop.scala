import org.lwjgl.input._
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.{Display, DisplayMode}

import scala.math._


object Loop {

    var finished = false
    var angle = 0.0f
    var rotation = 0.0f

    def main(args:Array[String]){
        init()
        run()
    }

    def init(){
        Display.setTitle(Config.title)
        Display.setFullscreen(true)
        Display.setVSyncEnabled(true)
        Display.setDisplayMode(new DisplayMode(Config.width,Config.height))
        Display.create()

        glEnable(GL_DEPTH_TEST)
        glEnable(GL_LIGHTING)
        glEnable(GL_LIGHT0)

        val aspect = Display.getDisplayMode.getWidth.toFloat/Display.getDisplayMode.getHeight.toFloat

        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()
        glFrustum(-aspect,aspect,-1,1,1,100)
        glMatrixMode(GL_MODELVIEW)
    }


    def cleanup(){
        Display.destroy()
    }

    def run(){
        while(!finished){
            Display.update()
            logic()
            render()
            Display.sync(Config.fps)
        }
    }

    def logic(){
        import Keyboard._

        if(isKeyDown(KEY_ESCAPE))
            finished = true
        if(Display.isCloseRequested)
            finished = true

        var ry = 0
        var rx = 0

        if(isKeyDown(KEY_I))
            ry += 1
        if(isKeyDown(KEY_K))
            ry -= 1
        if(isKeyDown(KEY_J))
            rx -= 1
        if(isKeyDown(KEY_L))
            rx += 1


        val ax = rx*cos(-rotation.toRadians)-ry*sin(-rotation.toRadians)
        val ay = rx*sin(-rotation.toRadians)+ry*cos(-rotation.toRadians)
        angle += 2.0f % 360
        rotation += 0.2f
    }

    def renderGrid(size : Int){
        glDisable(GL_LIGHTING)

        glBegin(GL_LINES)

        for(i <- -size to size){
            glVertex2i(i,-size)
            glVertex2i(i, size)
            glVertex2i(-size,i)
            glVertex2i( size,i)
        }

        glEnd()
        glEnable(GL_LIGHTING)
    }

    def render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity()
        glTranslatef(0,0,-20)
        glRotatef(-70,1,0,0)
        glRotatef(rotation,0,0,1)
        glColor3f(0,1,0)
        renderGrid(10000)
    }

}