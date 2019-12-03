#version 400 core

in vec2 passTexture;
in vec3 color;

layout(location = 0) out vec4 outColor;

uniform sampler2D textureSampler;

void main(void){
	outColor = texture(textureSampler, passTexture) * vec4(color,1.0);
}
