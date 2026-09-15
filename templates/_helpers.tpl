{{- define "clientes.srcChecksum" -}}
{{- $sum := "" -}}
{{- range $path, $_ := .Files.Glob "src/**/*.java" -}}
{{- $sum = printf "%s%s%s" $sum $path ($.Files.Get $path) -}}
{{- end -}}
{{- range $path, $_ := .Files.Glob "src/**/*.properties" -}}
{{- $sum = printf "%s%s%s" $sum $path ($.Files.Get $path) -}}
{{- end -}}
{{- $sum = printf "%s%s" $sum (.Files.Get "pom.xml") -}}
{{- $sum = printf "%s%s" $sum (.Files.Get "Dockerfile") -}}
{{- $sum | sha256sum -}}
{{- end -}}
