package com.idnp.openglsample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Pyramid {
    private FloatBuffer vertexBuffer;  // Buffer para los vertices
    private FloatBuffer colorBuffer;   // Buffer para generar los colores
    private ByteBuffer indexBuffer;    // Buffer para la creacion de los indices

    private float[] vertices = { // 5 vertices of the pyramid in (x,y,z)
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f, -1.0f,  1.0f,
            -1.0f, -1.0f,  1.0f,
            0.0f,  1.0f,  0.0f
    };

    private float[] colors = {  // Colors of the 5 vertices in RGBA
            0.0f, 0.0f, 1.0f, 1.0f,  // 0. Azul
            0.0f, 1.0f, 0.0f, 1.0f,  // 1. Verde
            0.0f, 0.0f, 1.0f, 1.0f,  // 2. Azul
            0.0f, 1.0f, 0.0f, 1.0f,  // 3. Verde
            1.0f, 0.0f, 0.0f, 1.0f   // 4. Rojo
    };

    private byte[] indices = { // Vertex indices of the 4 Triangles
            2, 4, 3,   // cara delantera
            1, 4, 2,   // cara derecha
            0, 4, 1,   // cara trasera
            4, 0, 3    // cara izquierda
    };

    // Constructor
    public Pyramid() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    // Draw the shape
    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);  // Genera la orientacion frente a la camara

        // Genera en pantalla los valores ya definidos en el constructor
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE,
                indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
