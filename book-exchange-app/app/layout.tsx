import type { ReactNode } from "react";
import { StoreProvider } from "./StoreProvider";
import "./styles/globals.css";
import styles from "./styles/layout.module.css";
import Navigation from "./components/Navigation";

interface Props {
  readonly children: ReactNode;
}

export default function RootLayout({ children }: Props) {
  return (
    <StoreProvider>
      <html lang="en">
        <body>
            <Navigation />
            <main className={styles.main}>{children}</main>
        </body>
      </html>
    </StoreProvider>
  );
}
