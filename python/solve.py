import matplotlib.pyplot as plt
import numpy as np
from scipy.integrate import solve_ivp

def format_num(value, assume_whole):
    if value == "Undefined": return value
    if assume_whole and isinstance(value, float) and value.is_integer(): return int(value)
    return value

def text_based_field(equation_str, x_init, x_final, x_step, y_init, y_final, y_step, assume_whole):
    results = []
    epsilon = 1e-9
    python_ready_equation = equation_str.replace('^', '**')

    y = y_init
    while y <= (y_final + epsilon):
        x = x_init
        while x <= (x_final + epsilon):
            try:
                slope = eval(python_ready_equation, {"__builtins__": None}, {"x": x, "y": y, "np": np})
                results.append((round(x, 3), round(y, 3), round(slope, 3)))
            except ZeroDivisionError:
                results.append((round(x, 3), round(y, 3), "Undefined"))
            except Exception as e:
                return f"Error: {e}"
            x += x_step
        y += y_step
    return results

def draw_slope_field(equation_str, x_min, x_max, y_min, y_max, x_step=1, y_step=1, show_curve=False, pt_x=0, pt_y=0):
    python_ready_equation = equation_str.replace('^', '**')

    try:
        eval(python_ready_equation, {"__builtins__": None}, {"x": 1, "y": 1, "np": np})
    except ZeroDivisionError:
        pass 
    except Exception as e:
        print(f"\n[!] ERROR: The math equation is invalid.")
        print(f"[!] Did you forget a '*' for multiplication? (e.g., use '3*x*y' instead of '3xy')")
        print(f"[!] Python error: {e}")
        return

    x = np.arange(x_min, x_max + 1e-9, x_step)
    y = np.arange(y_min, y_max + 1e-9, y_step)
    X, Y = np.meshgrid(x, y)
    
    U = np.zeros_like(X, dtype=float)
    V = np.zeros_like(X, dtype=float)
    
    for i in range(X.shape[0]):
        for j in range(X.shape[1]):
            try:
                slope = eval(python_ready_equation, {"__builtins__": None}, {"x": X[i,j], "y": Y[i,j], "np": np})
                U[i,j] = 1.0
                V[i,j] = float(slope)
            except ZeroDivisionError:
                U[i,j] = 0.0
                V[i,j] = 1.0 
            except:
                U[i,j] = 0.0
                V[i,j] = 0.0
                
    N = np.sqrt(U**2 + V**2)
    N[N == 0] = 1 
    U, V = U/N, V/N

    plt.figure(figsize=(8, 8))
    plt.axhline(0, color='black', linewidth=1.5) 
    plt.axvline(0, color='black', linewidth=1.5)

    plt.quiver(X, Y, U, V, pivot='mid', headwidth=0, headlength=0, headaxislength=0, color='black')
    
    if show_curve:
        def dy_dx_scipy(t, y_val):
            try:
                slope = eval(python_ready_equation, {"__builtins__": None}, {"x": t, "y": y_val[0], "np": np})
                if slope > 10000: return 10000
                if slope < -10000: return -10000
                return slope
            except:
                return 0
                
        sol_fwd = solve_ivp(dy_dx_scipy, [pt_x, x_max], [pt_y], dense_output=True)
        sol_bwd = solve_ivp(dy_dx_scipy, [pt_x, x_min], [pt_y], dense_output=True)
        
        t_fwd = np.linspace(pt_x, x_max, 100)
        t_bwd = np.linspace(pt_x, x_min, 100)
        plt.plot(t_fwd, sol_fwd.sol(t_fwd)[0], color='blue', linewidth=2, label="Solution Curve")
        plt.plot(t_bwd, sol_bwd.sol(t_bwd)[0], color='blue', linewidth=2)
        plt.plot(pt_x, pt_y, 'ro', label=f'Initial Point ({pt_x}, {pt_y})')
        plt.legend()

    plt.title(f"Slope Field for dy/dx = {equation_str}")
    plt.xlim([x_min - 0.5, x_max + 0.5])
    plt.ylim([y_min - 0.5, y_max + 0.5])
    
    if float(x_step).is_integer(): plt.xticks(np.arange(x_min, x_max + 1, x_step))
    if float(y_step).is_integer(): plt.yticks(np.arange(y_min, y_max + 1, y_step))
    
    plt.grid(True, linestyle='--', alpha=0.5)
    plt.show()

print("[F] Field-Only Graph (Just the slope segments)")
print("[G] Graph with Curve (Requires starting point)")
print("[T] Text Table Mode (Raw numbers)")
mode_choice = input("Select a mode (F/G/T): ").strip().upper()

print("\nNote: Use '*' for multiplication (e.g., -x * (y - 1)^2 )")
user_equation = input("Enter dy/dx = ")

if mode_choice in ['F', 'G']:
    print("\n-- Graphing Bounds --")
    x_initial = float(input("Min x (e.g., -6): "))
    x_final = float(input("Max x (e.g., 6): "))
    y_initial = float(input("Min y (e.g., -6): "))
    y_final = float(input("Max y (e.g., 6): "))
    
    if mode_choice == 'G':
        print("\n-- Initial Value Problem (Solution Curve Point) --")
        pt_x = float(input("Starting x: "))
        pt_y = float(input("Starting y: "))
        print("\nGenerating Graph...")
        draw_slope_field(user_equation, x_initial, x_final, y_initial, y_final, show_curve=True, pt_x=pt_x, pt_y=pt_y)
    else:
        print("\nGenerating Graph...")
        draw_slope_field(user_equation, x_initial, x_final, y_initial, y_final, x_step=1, y_step=1, show_curve=False)

elif mode_choice == 'T':
    print("\n-- Grid Parameters --")
    x_initial = float(input("Initial x: "))
    x_step = float(input("Skip/Step x: "))
    x_final = float(input("Final x: "))

    y_initial = float(input("Initial y: "))
    y_step = float(input("Skip/Step y: "))
    y_final = float(input("Final y: "))

    whole_num_choice = input("Assume whole numbers? (Type 'yes' to hide .0): ").strip().lower()
    assume_whole = (whole_num_choice in ['yes', 'y'])

    print("\nCalculating...\n")
    points = text_based_field(user_equation, x_initial, x_final, x_step, y_initial, y_final, y_step, assume_whole)
    
    if isinstance(points, str):
        print(points) 
    else:
        print(f"{'x':<6} | {'y':<6} | {'dy/dx (Slope)':<10}")
        print("-" * 28)
        for x, y, slope in points:
            print(f"{format_num(x, assume_whole):<6} | {format_num(y, assume_whole):<6} | {format_num(slope, assume_whole):<10}")
else:
    print("Invalid mode selected. Please restart.")
