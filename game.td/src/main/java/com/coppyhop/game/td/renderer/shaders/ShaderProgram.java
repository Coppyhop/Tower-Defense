package com.coppyhop.game.td.renderer.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

/**
 * ShaderProgram
 * 
 * Represents a Shader Program. Contains the IDs of where the programs exist
 * in memory and has various methods to facilitate ease of use of a shader
 * program. As every shader is different, I have elected to make this class
 * abstract so that any other shader programs need to be defined by the 
 * developer as a class. 
 * 
 * @author kyle
 *
 */
public abstract class ShaderProgram {
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	public ShaderProgram(String vertexFile, String fragmentFile) {
		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		bindAttributes();
		getAllUniformLocations();
	}
	
	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	protected void loadMatrix4f(int location, Matrix4f value) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			  FloatBuffer fb = new Matrix4f(value).get(stack.mallocFloat(16));
			  GL20.glUniformMatrix4fv(location, false, fb);
		}
	}
	
	protected void loadVector3f(int location, Vector3f value) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			  FloatBuffer fb = new Vector3f(value).get(stack.mallocFloat(3));
			  GL20.glUniform3fv(location, fb);
		}
	}
	
	public void start() {
		GL20.glUseProgram(programID);
	}
	
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	/**
	 * CleanUp
	 * 
	 * Destroys the program and its attached shaders, removing them from
	 * memory.
	 */
	public void cleanUp() {
		stop();
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
	}
	
	protected abstract void bindAttributes();
	
	protected abstract void getAllUniformLocations();
	
	protected void bindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	
	/**
	 * Loads the given file into memory, compiling it as a shader and readying
	 * it for use in OpenGL. 
	 * 
	 * @param file The file that we will load up and compile
	 * @param type The type of shader that this is
	 * @return int The id of the shader we just compiled
	 */
	private static int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line= reader.readLine()) !=null) {
				shaderSource.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.exit(1);
		}
		return shaderID;
	}
}
