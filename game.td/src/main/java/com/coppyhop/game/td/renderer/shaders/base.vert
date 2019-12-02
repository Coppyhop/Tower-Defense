#version 150 core

in vec3 position;

out vec3 color;

void main(void) {
	gl_Position = vec4(position,1.0);
	color = vec3(position.x+1,1.0,position.y+1);
}
