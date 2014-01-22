/*
 * Copyright 1998-2014 Konstantin Bulenkov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
