package com.intellij.examples;

import com.intellij.diagram.DiagramVfsResolver;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * @author Konstantin Bulenkov
 */
public class FileDiagramVfsResolver implements DiagramVfsResolver<VirtualFile> {
  @Override
  public String getQualifiedName(VirtualFile file) {
    return file.getPath();
  }

  @Nullable
  @Override
  public VirtualFile resolveElementByFQN(String s, Project project) {
    return LocalFileSystem.getInstance().findFileByIoFile(new File(s));
  }
}
