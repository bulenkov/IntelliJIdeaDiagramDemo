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

import com.intellij.diagram.DiagramNodeBase;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class FileNode extends DiagramNodeBase<VirtualFile> {
  private final VirtualFile myFile;

  public FileNode(VirtualFile file) {
    super(FileDiagramProvider.getInstance());
    myFile = file;
  }

  @Nullable
  @Override
  public String getTooltip() {
    return getIdentifyingElement().getCanonicalPath();
  }

  @Override
  public Icon getIcon() {
    return myFile.isDirectory() ? AllIcons.Nodes.Folder : myFile.getFileType().getIcon();
  }

  @NotNull
  @Override
  public VirtualFile getIdentifyingElement() {
    return myFile;
  }
}
