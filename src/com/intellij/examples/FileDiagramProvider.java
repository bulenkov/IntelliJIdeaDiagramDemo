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

import com.intellij.diagram.*;
import com.intellij.diagram.extras.DiagramExtras;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Konstantin Bulenkov
 */
public class FileDiagramProvider extends BaseDiagramProvider<VirtualFile> {

  public static final String ID = "FileDiagramProvider";
  private DiagramElementManager<VirtualFile> myElementManager = new FileDiagramElementManager();
  private DiagramVfsResolver<VirtualFile> myVfsResolver = new FileDiagramVfsResolver();
  private DiagramExtras<VirtualFile> myExtras = new FileDiagramExtras();
  private DiagramColorManager myColorManager = new FileDiagramColorManager();

  @Pattern("[a-zA-Z0-9_-]*")
  @Override
  public String getID() {
    return ID;
  }

  @Override
  public DiagramElementManager<VirtualFile> getElementManager() {
    return myElementManager;
  }

  @Override
  public DiagramVfsResolver<VirtualFile> getVfsResolver() {
    return myVfsResolver;
  }

  @Override
  public String getPresentableName() {
    return "File Diagram";
  }

  @NotNull
  @Override
  public DiagramExtras<VirtualFile> getExtras() {
    return myExtras;
  }

  @Override
  public FileDiagramDataModel createDataModel(@NotNull Project project, @Nullable VirtualFile file, @Nullable VirtualFile file2, DiagramPresentationModel model) {
    return new FileDiagramDataModel(project, file);
  }

  @Override
  public DiagramColorManager getColorManager() {
    return myColorManager;
  }

  public static FileDiagramProvider getInstance() {
    return (FileDiagramProvider)DiagramProvider.findByID(ID);
  }
}
